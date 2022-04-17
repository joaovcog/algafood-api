package com.algaworks.algafood.api.dto.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDto {
	
	@Valid
	@NotNull
	private CodRestauranteInputDto restaurante;
	
	@Valid
	@NotNull
	private CodFormaPagamentoInputDto formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInputDto enderecoEntrega;
	
	@Valid
	@Size(min = 1)
	@NotNull
	private List<ItemPedidoInputDto> itens;
	
}
