package com.algaworks.algafood.api.v1.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodCozinhaInputDto {

	@NotNull
	private Long codigo;
	
}
