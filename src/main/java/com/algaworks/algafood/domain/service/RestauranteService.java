package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.stream.Collectors;

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
	
//	@Transactional
//	public void ativar(List<Long> codRestaurantes) {
//		codRestaurantes.forEach(this::ativar);
//	}
	
//	@Transactional
//	public void inativar(List<Long> codRestaurantes) {
//		codRestaurantes.forEach(this::inativar);
//	}
	
	@Transactional
	public void ativar(List<Long> codRestaurantes) {
		List<Restaurante> restaurantes = buscarOuFalhar(codRestaurantes);
		
		restaurantes.forEach(Restaurante::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> codRestaurantes) {
		List<Restaurante> restaurantes = buscarOuFalhar(codRestaurantes);
		
		restaurantes.forEach(Restaurante::inativar);
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
	
	public List<Restaurante> buscarOuFalhar(List<Long> codRestaurantes) {
		List<Restaurante> restaurantes = restauranteRepository.findByCodigoIn(codRestaurantes);
		
		if (codRestaurantes.size() != restaurantes.size()) {
			List<Long> codInvalidos = obterCodigosRestaurantesNaoEncontrados(codRestaurantes, restaurantes);
			
			throw new RestauranteNaoEncontradoException(criarMensagemParaVariosRestaurantesNaoEncontrados(codInvalidos));
		}
		
		return restaurantes;
	}
	
	private List<Long> obterCodigosRestaurantesNaoEncontrados(List<Long> codRestaurantes, List<Restaurante> restaurantes) {
		List<Long> codValidos = restaurantes.stream().map(r -> r.getCodigo()).collect(Collectors.toList());
		List<Long> codInvalidos = codRestaurantes;
		
		codValidos.stream().forEach(c -> codInvalidos.remove(c));
		
		return codInvalidos;
	}
	
	private String criarMensagemParaVariosRestaurantesNaoEncontrados(List<Long> codRestaurantes) {
		final String mensagemFixa = "Não existe um cadastro de restaurante para os seguintes códigos: ";
		
		String mensagemFormatada = codRestaurantes.stream().map((Long c) -> {
			if (c != null) {
				return c.toString();
			}
			
			return "";
		}).collect(Collectors.joining(", ", mensagemFixa, ""));

		return mensagemFormatada;
	}
}
