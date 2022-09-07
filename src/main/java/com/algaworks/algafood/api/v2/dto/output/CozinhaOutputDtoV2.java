package com.algaworks.algafood.api.v2.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaOutputDtoV2 extends RepresentationModel<CozinhaOutputDtoV2> {

	private Long codigoCozinha;
	
	private String nomeCozinha;
	
}
