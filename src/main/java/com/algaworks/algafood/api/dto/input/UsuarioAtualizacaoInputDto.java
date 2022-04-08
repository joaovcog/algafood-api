package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAtualizacaoInputDto implements UsuarioInputDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
}
