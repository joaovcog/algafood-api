package com.algaworks.algafood.api.v2.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDtoV2 {
	
	@NotBlank
	private String nomeCozinha;
	
}
