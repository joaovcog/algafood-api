package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.CozinhaInputDto;
import com.algaworks.algafood.api.v1.dto.output.CozinhaOutputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoAssembler extends GenericInputOutputAssembler<Cozinha, CozinhaInputDto, CozinhaOutputDto> {
	
}
