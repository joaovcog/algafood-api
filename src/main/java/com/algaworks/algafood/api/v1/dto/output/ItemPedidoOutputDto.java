package com.algaworks.algafood.api.v1.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoOutputDto {
	
	private Long codigoProduto;
	private String nomeProduto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
}
