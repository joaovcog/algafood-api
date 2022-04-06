package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.RestauranteOutputDto;
import com.algaworks.algafood.api.model.input.RestauranteInputDto;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoAssembler extends GenericAssembler<Restaurante, RestauranteInputDto, RestauranteOutputDto> {
	
	@Override
	public void copyFromInputDtoToDomainObject(RestauranteInputDto inputDto, Restaurante entity) {
		entity.setCozinha(new Cozinha());
		super.copyFromInputDtoToDomainObject(inputDto, entity);
	}
	
}
