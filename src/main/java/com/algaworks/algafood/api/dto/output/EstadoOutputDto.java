package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoOutputDto extends RepresentationModel<EstadoOutputDto> {
	
	private Long codigo;
	
	private String nome;
	
}
