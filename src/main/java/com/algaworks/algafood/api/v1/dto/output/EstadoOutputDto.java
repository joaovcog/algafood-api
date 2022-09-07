package com.algaworks.algafood.api.v1.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoOutputDto extends RepresentationModel<EstadoOutputDto> {
	
	@Schema(example = "1")
	private Long codigo;
	
	@Schema(example = "Minas Gerais")
	private String nome;
	
}
