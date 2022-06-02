package com.algaworks.algafood.api.dto.output;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoOutputDto extends RepresentationModel<FormaPagamentoOutputDto> {
	
	private Long codigo;
	private String descricao;
	
}
