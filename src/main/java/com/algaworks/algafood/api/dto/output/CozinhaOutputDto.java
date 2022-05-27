package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algaworks.algafood.api.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaOutputDto extends RepresentationModel<CozinhaOutputDto> {

	@JsonView(RestauranteView.Resumo.class)
	private Long codigo;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
