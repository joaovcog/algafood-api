package com.algaworks.algafood.api.v1.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioOutputDto extends RepresentationModel<UsuarioOutputDto> {
	
	private Long codigo;
	private String nome;
	private String email;
	
}
