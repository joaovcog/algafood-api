package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.v1.dto.output.CidadeOutputDto;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDtoAssembler extends GenericInputOutputAssembler<Cidade, CidadeInputDto, CidadeOutputDto> {
	
	@Override
	public void copyFromInputDtoToDomainObject(CidadeInputDto inputDto, Cidade entity) {
		entity.setEstado(new Estado());
		
		super.copyFromInputDtoToDomainObject(inputDto, entity);
	}
	
}
