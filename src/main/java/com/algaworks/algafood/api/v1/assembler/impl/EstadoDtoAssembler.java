package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.EstadoInputDto;
import com.algaworks.algafood.api.v1.dto.output.EstadoOutputDto;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDtoAssembler extends GenericInputOutputAssembler<Estado, EstadoInputDto, EstadoOutputDto> {
	
}
