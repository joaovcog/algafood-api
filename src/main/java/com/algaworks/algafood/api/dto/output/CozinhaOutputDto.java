package com.algaworks.algafood.api.dto.output;

import com.algaworks.algafood.api.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaOutputDto {

	@JsonView(RestauranteView.Resumo.class)
	private Long codigo;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
