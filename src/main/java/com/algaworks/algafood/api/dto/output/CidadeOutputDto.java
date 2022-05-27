package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeOutputDto extends RepresentationModel<CidadeOutputDto> {
	
	//@ApiModelProperty(value = "Código da cidade", example = "1")
	@ApiModelProperty(example = "1")
	private Long codigo;
	
	@ApiModelProperty(example = "Itumbiara")
	private String nome;
	private EstadoOutputDto estado;
	
}
