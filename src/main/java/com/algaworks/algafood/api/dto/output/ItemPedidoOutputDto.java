package com.algaworks.algafood.api.dto.output;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoOutputDto extends RepresentationModel<ItemPedidoOutputDto> {
	
	private Long codigoProduto;
	private String nomeProduto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
}
