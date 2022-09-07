package com.algaworks.algafood.api.v1.assembler.old;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.dto.input.RestauranteInputDto;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteDtoDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager manager;
	
	public Restaurante toDomainObjectFromInputDto(RestauranteInputDto restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyFromInputDtoToDomainObject(RestauranteInputDto restauranteInput, Restaurante restaurante) {
		restaurante.setCozinha(new Cozinha()); //evitar exception de alteração de identificador da entidade pelo JPA
		//manager.detach(restaurante.getCozinha());
		
		modelMapper.map(restauranteInput, restaurante);
	}
	
}
