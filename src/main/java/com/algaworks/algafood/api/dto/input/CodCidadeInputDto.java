package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodCidadeInputDto {

	@Schema(example = "1")
	@NotNull
	private Long codigo;
	
}
