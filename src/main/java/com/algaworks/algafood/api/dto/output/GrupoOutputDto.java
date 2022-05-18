package com.algaworks.algafood.api.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoOutputDto {
	
	@ApiModelProperty(example = "1")
	private Long codigo;
	
	@ApiModelProperty(example = "Gerente")
	private String nome;
	
}
