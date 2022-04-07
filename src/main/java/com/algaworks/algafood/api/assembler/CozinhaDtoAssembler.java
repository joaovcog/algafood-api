package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.CozinhaInputDto;
import com.algaworks.algafood.api.dto.output.CozinhaOutputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoAssembler extends GenericAssembler<Cozinha, CozinhaInputDto, CozinhaOutputDto> {
	
}
