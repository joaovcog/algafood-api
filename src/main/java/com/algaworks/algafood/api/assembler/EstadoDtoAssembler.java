package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.EstadoOutputDto;
import com.algaworks.algafood.api.model.input.EstadoInputDto;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDtoAssembler extends GenericAssembler<Estado, EstadoInputDto, EstadoOutputDto> {
	
}
