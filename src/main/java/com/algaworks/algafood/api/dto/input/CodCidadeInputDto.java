package com.algaworks.algafood.api.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodCidadeInputDto {

	@NotNull
	private Long codigo;
	
}