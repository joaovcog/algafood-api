package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeOutputDto extends RepresentationModel<CidadeOutputDto> {
	
	private Long codigo;
	
	private String nome;
	private EstadoOutputDto estado;
	
}
