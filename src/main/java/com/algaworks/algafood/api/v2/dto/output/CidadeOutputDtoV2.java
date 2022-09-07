package com.algaworks.algafood.api.v2.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeOutputDtoV2 extends RepresentationModel<CidadeOutputDtoV2> {
	
	@Schema(example = "1")
	private Long codigoCidade;
	
	@Schema(example = "Uberlândia")
	private String nomeCidade;
	
	private Long codigoEstado;
	private String nomeEstado;
	
}
