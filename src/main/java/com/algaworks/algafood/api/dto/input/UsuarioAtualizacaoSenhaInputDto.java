package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAtualizacaoSenhaInputDto implements UsuarioInputDto {
	
	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	@Size(min = 5, max = 20)
	private String novaSenha;
	
}
