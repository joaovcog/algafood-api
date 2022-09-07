package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.PermissaoInputDto;
import com.algaworks.algafood.api.v1.dto.output.PermissaoOutputDto;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoDtoAssembler extends GenericInputOutputAssembler<Permissao, PermissaoInputDto, PermissaoOutputDto> {
	
}
