package com.algaworks.algafood.api.v1.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInputDto {
	
	@NotBlank
	private String descricao;
	
}
