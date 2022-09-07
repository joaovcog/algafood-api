package com.algaworks.algafood.api.v1.dto.output;

import java.math.BigDecimal;

import com.algaworks.algafood.api.v1.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteOutputDto {
	
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private Long codigo;
	
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaOutputDto cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoOutputDto endereco;
	
}
