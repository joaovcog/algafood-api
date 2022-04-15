package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.PermissaoInputDto;
import com.algaworks.algafood.api.dto.output.PermissaoOutputDto;
import com.algaworks.algafood.domain.model.Permissao;

@Component
public class PermissaoDtoAssembler extends GenericAssembler<Permissao, PermissaoInputDto, PermissaoOutputDto> {
	
}
