package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CozinhaOutputDto;
import com.algaworks.algafood.api.model.input.CozinhaInputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoAssembler extends GenericAssembler<Cozinha, CozinhaInputDto, CozinhaOutputDto> {
	
}
