package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CozinhaDTO;
import com.algaworks.algafood.api.model.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoAssembler {
	
	public RestauranteDTO toModel(Restaurante restaurante) {
		RestauranteDTO restauranteDTO = new RestauranteDTO();

		restauranteDTO.setCodigo(restaurante.getCodigo());
		restauranteDTO.setNome(restaurante.getNome());
		restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());

		CozinhaDTO cozinhaDTO = new CozinhaDTO();
		cozinhaDTO.setCodigo(restaurante.getCozinha().getCodigo());
		cozinhaDTO.setNome(restaurante.getCozinha().getNome());

		restauranteDTO.setCozinha(cozinhaDTO);

		return restauranteDTO;
	}

	public List<RestauranteDTO> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
	
}
