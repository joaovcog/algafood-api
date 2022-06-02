package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteResumoOutputDto extends RepresentationModel<RestauranteResumoOutputDto> {
	
	private Long codigo;
	private String nome;
	
}
