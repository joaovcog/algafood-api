package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDto {
	
	@NotBlank
	private String nome;
	
}
