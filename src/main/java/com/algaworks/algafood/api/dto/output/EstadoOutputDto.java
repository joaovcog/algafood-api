package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Getter
@Setter
public class EstadoOutputDto extends RepresentationModel<EstadoOutputDto> {
	
	@ApiModelProperty(example = "1")
	private Long codigo;
	
	@ApiModelProperty(example = "Goiás")
	private String nome;
	
}
