package com.algaworks.algafood.api.v2.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.assembler.generic.GenericInputOutputAssemblerV2;
import com.algaworks.algafood.api.v2.dto.input.CidadeInputDtoV2;
import com.algaworks.algafood.api.v2.dto.output.CidadeOutputDtoV2;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDtoAssemblerV2 extends GenericInputOutputAssemblerV2<Cidade, CidadeInputDtoV2, CidadeOutputDtoV2> {
	
	@Override
	public void copyFromInputDtoToDomainObject(CidadeInputDtoV2 inputDto, Cidade entity) {
		entity.setEstado(new Estado());
		
		super.copyFromInputDtoToDomainObject(inputDto, entity);
	}
	
}
