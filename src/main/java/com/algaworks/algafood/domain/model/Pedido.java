package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	
	@ManyToOne
	@JoinColumn(name = "cod_usuario_cliente", nullable = false)
	private Usuario usuarioCliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_restaurante", nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
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
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_confirmacao")
	private LocalDateTime dataConfirmacao;
	
	@Column(name = "data_cancelamento")
	private LocalDateTime dataCancelamento;
	
	@Column(name = "data_entrega")
	private LocalDateTime dataEntrega;
	
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();

}
