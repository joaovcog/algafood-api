package com.algaworks.algafood.api.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeOutputDto {
	
	//@ApiModelProperty(value = "Código da cidade", example = "1")
	@ApiModelProperty(example = "1")
	private Long codigo;
	
	@ApiModelProperty(example = "Itumbiara")
	private String nome;
	private EstadoOutputDto estado;
	
}
