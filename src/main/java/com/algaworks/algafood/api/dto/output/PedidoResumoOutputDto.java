package com.algaworks.algafood.api.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoOutputDto extends RepresentationModel<PedidoResumoOutputDto> {

	private String identificador;
	private UsuarioOutputDto usuarioCliente;
	private RestauranteResumoOutputDto restaurante;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private String status;

}
