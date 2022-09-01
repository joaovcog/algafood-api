package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeOutputDto extends RepresentationModel<CidadeOutputDto> {
	
	@Schema(example = "1")
	private Long codigo;
	
	@Schema(example = "Uberlândia")
	private String nome;
	private EstadoOutputDto estado;
	
}
