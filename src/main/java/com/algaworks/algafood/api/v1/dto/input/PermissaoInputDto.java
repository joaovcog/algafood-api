package com.algaworks.algafood.api.v1.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoInputDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
}
