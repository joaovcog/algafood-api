package com.algaworks.algafood.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOutputDto {
	
	private Long codigo;
	private String nome;
	private EstadoOutputDto estado;
	
}
