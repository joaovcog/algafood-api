package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CidadeService cidadeService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long codCozinha = restaurante.getCozinha().getCodigo();
		Long codCidade = restaurante.getEndereco().getCidade().getCodigo();
		
		Cozinha cozinha = cozinhaService.buscarOuFalhar(codCozinha);
		Cidade cidade = cidadeService.buscarOuFalhar(codCidade);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long codRestaurante) {
		Restaurante restauranteAtual = buscarOuFalhar(codRestaurante);
		
		restauranteAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long codRestaurante) {
		Restaurante restauranteAtual = buscarOuFalhar(codRestaurante);
		
		restauranteAtual.inativar();
	}

	public Restaurante buscarOuFalhar(Long codRestaurante) {
		return restauranteRepository.findById(codRestaurante)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(codRestaurante));
	}
}
