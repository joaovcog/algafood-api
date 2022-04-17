package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInputDto {
	
	@NotNull
	private Long codigoProduto;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	private String observacao;
	
}
