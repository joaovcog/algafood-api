package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeResumoOutputDto extends RepresentationModel<CidadeResumoOutputDto> {
	
	private Long codigo;
	private String nome;
	private String estado;
	
}
