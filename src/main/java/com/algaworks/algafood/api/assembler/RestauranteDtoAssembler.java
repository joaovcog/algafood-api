package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CozinhaOutputDto;
import com.algaworks.algafood.api.model.RestauranteOutputDto;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoAssembler {
	
	public RestauranteOutputDto toModel(Restaurante restaurante) {
		RestauranteOutputDto restauranteDTO = new RestauranteOutputDto();

		restauranteDTO.setCodigo(restaurante.getCodigo());
		restauranteDTO.setNome(restaurante.getNome());
		restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());

		CozinhaOutputDto cozinhaDTO = new CozinhaOutputDto();
		cozinhaDTO.setCodigo(restaurante.getCozinha().getCodigo());
		cozinhaDTO.setNome(restaurante.getCozinha().getNome());

		restauranteDTO.setCozinha(cozinhaDTO);

		return restauranteDTO;
	}

	public List<RestauranteOutputDto> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
	
}
