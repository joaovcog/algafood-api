package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.RestauranteInputDto;
import com.algaworks.algafood.api.v1.dto.output.RestauranteOutputDto;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoAssembler extends GenericInputOutputAssembler<Restaurante, RestauranteInputDto, RestauranteOutputDto> {
	
	@Override
	public void copyFromInputDtoToDomainObject(RestauranteInputDto inputDto, Restaurante entity) {
		entity.setCozinha(new Cozinha());
		
		if (entity.getEndereco() != null) {
			entity.getEndereco().setCidade(new Cidade());
		}
		
		super.copyFromInputDtoToDomainObject(inputDto, entity);
	}
	
}
