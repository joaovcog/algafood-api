package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDto {
	
	@ApiModelProperty(example = "Gerente", required = true)
	@NotBlank
	private String nome;
	
}
