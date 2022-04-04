package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInputDto;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObjectFromInputDto(RestauranteInputDto restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
}
