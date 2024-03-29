package com.algaworks.algafood.api.v1.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInputDto {
	
	@Schema(example = "Uberlândia")
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private CodEstadoInputDto estado;
	
}
