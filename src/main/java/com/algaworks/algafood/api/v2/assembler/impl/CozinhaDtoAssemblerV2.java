package com.algaworks.algafood.api.v2.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.assembler.generic.GenericInputOutputAssemblerV2;
import com.algaworks.algafood.api.v2.dto.input.CozinhaInputDtoV2;
import com.algaworks.algafood.api.v2.dto.output.CozinhaOutputDtoV2;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoAssemblerV2 extends GenericInputOutputAssemblerV2<Cozinha, CozinhaInputDtoV2, CozinhaOutputDtoV2> {
	
}
