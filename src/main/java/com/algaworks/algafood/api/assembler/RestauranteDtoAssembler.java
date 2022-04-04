package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.RestauranteOutputDto;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteOutputDto toOutputDtoFromModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteOutputDto.class);
	}

	public List<RestauranteOutputDto> toCollectionOutputDtoFromModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toOutputDtoFromModel(restaurante)).collect(Collectors.toList());
	}
	
}
