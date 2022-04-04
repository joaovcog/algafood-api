package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInputDto;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDtoDisassembler {

	public Restaurante toDomainObjectFromInputDto(RestauranteInputDto restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setCodigo(restauranteInput.getCozinha().getCodigo());
		
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
	
}
