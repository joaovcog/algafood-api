package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteOutputDto {
	
	private Long codigo;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaOutputDto cozinha;
	private Boolean ativo;
	
}
