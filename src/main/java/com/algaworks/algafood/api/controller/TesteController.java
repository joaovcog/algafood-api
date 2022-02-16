package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome-cozinha")
	public List<Restaurante> restaurantesPorNome(String nome, Long codCozinha) {
		return restauranteRepository.consultarPorNome(nome, codCozinha);
	}
	
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> primeiroRestaurantePorNome(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> primeirosDoisRestaurantesPorNome(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/existe")
	public boolean cozinhaExiste(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-nome-taxa-frete")
	public List<Restaurante> restaurantesPorNomeTaxaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.consultar(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> primeiroRestaurante(String nome) {
		return restauranteRepository.buscarPrimeiro();
	}
	
}
