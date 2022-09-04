package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDto {
	
	@Schema(example = "Administrador")
	@NotBlank
	private String nome;
	
}
