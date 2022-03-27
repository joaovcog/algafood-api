package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	public Restaurante salvar(Restaurante restaurante) {
		Long codCozinha = restaurante.getCozinha().getCodigo();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(codCozinha);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarOuFalhar(Long codRestaurante) {
		return restauranteRepository.findById(codRestaurante)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(codRestaurante));
	}
}
