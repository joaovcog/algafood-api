package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOutputDto {
	
	private Long codigo;
	private String nome;
	private EstadoOutputDto estado;
	
}
