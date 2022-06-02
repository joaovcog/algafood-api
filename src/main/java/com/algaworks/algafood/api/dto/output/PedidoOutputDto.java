package com.algaworks.algafood.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoOutputDto extends RepresentationModel<PedidoOutputDto> {

	private String identificador;
	private UsuarioOutputDto usuarioCliente;
	private RestauranteResumoOutputDto restaurante;
	private FormaPagamentoOutputDto formaPagamento;
	private EnderecoOutputDto enderecoEntrega;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private String status;
	private List<ItemPedidoOutputDto> itens;

}
