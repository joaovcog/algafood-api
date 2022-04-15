package com.algaworks.algafood.api.dto.output;

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
	private Boolean aberto;
	private EnderecoOutputDto endereco;
	
}
