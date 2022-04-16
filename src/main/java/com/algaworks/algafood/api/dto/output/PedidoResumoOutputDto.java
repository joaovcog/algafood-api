package com.algaworks.algafood.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumoOutputDto {

	private Long codigo;
	private UsuarioOutputDto usuarioCliente;
	private RestauranteResumoOutputDto restaurante;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private String status;

}
