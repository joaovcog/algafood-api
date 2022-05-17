package com.algaworks.algafood.api.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoOutputDto {
	
	@ApiModelProperty(example = "1")
	private Long codigo;
	
	@ApiModelProperty(example = "Goiás")
	private String nome;
	
}
