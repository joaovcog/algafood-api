package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
public class GrupoOutputDto {
	
	@Schema(example = "1")
	private Long codigo;
	
	@Schema(example = "Administrador")
	private String nome;
	
}
