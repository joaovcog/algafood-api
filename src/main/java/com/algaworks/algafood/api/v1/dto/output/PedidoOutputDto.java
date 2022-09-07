package com.algaworks.algafood.api.v1.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoOutputDto {

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
