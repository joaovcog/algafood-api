package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	private String identificador;

	@ManyToOne
	@JoinColumn(name = "cod_usuario_cliente", nullable = false)
	private Usuario usuarioCliente;

	@ManyToOne
	@JoinColumn(name = "cod_restaurante", nullable = false)
	private Restaurante restaurante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;

	@Embedded
	private Endereco enderecoEntrega;

	@Column(nullable = false)
	private BigDecimal subtotal;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;

	@CreationTimestamp
	@Column(name = "data_criacao", nullable = false)
	private OffsetDateTime dataCriacao;

	@Column(name = "data_confirmacao")
	private OffsetDateTime dataConfirmacao;

	@Column(name = "data_cancelamento")
	private OffsetDateTime dataCancelamento;

	@Column(name = "data_entrega")
	private OffsetDateTime dataEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);

		this.subtotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);

		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now(ZoneOffset.UTC));
	}

	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now(ZoneOffset.UTC));
	}

	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now(ZoneOffset.UTC));
	}

	private void setStatus(StatusPedido novoStatus) {
		if (getStatus().naoPodeAlterarPara(novoStatus)) {
			final String mensagem;

			if (getStatus().equals(novoStatus)) {
				mensagem = String.format("Pedido %s já foi %s.", getIdentificador(), novoStatus.getDescricao());
			} else {
				mensagem = String.format("Status do pedido %s não pode ser alterado de %s para %s.", getIdentificador(),
						getStatus().getDescricao(), novoStatus.getDescricao());
			}

			throw new NegocioException(mensagem);
		}

		this.status = novoStatus;
	}
	
	@PrePersist
	private void gerarIdentificador() {
		setIdentificador(UUID.randomUUID().toString());
	}

}
