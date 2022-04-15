package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.exception.UsuarioNaoVinculadoRestauranteException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private UsuarioService usuarioService;

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
	
	@Transactional
	public void abrir(Long codRestaurante) {
		Restaurante restauranteAtual = buscarOuFalhar(codRestaurante);

		restauranteAtual.abrir();
	}
	
	@Transactional
	public void fechar(Long codRestaurante) {
		Restaurante restauranteAtual = buscarOuFalhar(codRestaurante);

		restauranteAtual.fechar();
	}

	@Transactional
	public void vincularFormaPagamento(Long codRestaurante, Long codFormaPagamento) {
		Restaurante restaurante = buscarOuFalhar(codRestaurante);

		if (restaurante.naoTemFormaPagamento(codFormaPagamento)) {
			FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(codFormaPagamento);

			restaurante.vincularFormaPagamento(formaPagamento);
		}
	}

	@Transactional
	public void desvincularFormaPagamento(Long codRestaurante, Long codFormaPagamento) {
		Restaurante restaurante = buscarOuFalhar(codRestaurante);
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(codFormaPagamento);

		restaurante.desvincularFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void vincularUsuarioResponsavel(Long codRestaurante, Long codUsuario) {
		Restaurante restaurante = buscarOuFalhar(codRestaurante);
		
		if (restaurante.naoTemUsuarioResponsavel(codUsuario)) {
			Usuario usuario = usuarioService.buscarOuFalhar(codUsuario);
			
			restaurante.vincularUsuarioResponsavel(usuario);
		}
	}
	
	@Transactional
	public void desvincularUsuarioResponsavel(Long codRestaurante, Long codUsuario) {
		Restaurante restaurante = buscarOuFalhar(codRestaurante);
		
		if (restaurante.naoTemUsuarioResponsavel(codUsuario)) {
			throw new UsuarioNaoVinculadoRestauranteException(codRestaurante, codUsuario);
		}
		
		Usuario usuario = usuarioService.buscarOuFalhar(codUsuario);
		
		restaurante.desvincularUsuarioResponsavel(usuario);
	}

	public Restaurante buscarOuFalhar(Long codRestaurante) {
		return restauranteRepository.findById(codRestaurante)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(codRestaurante));
	}
}
