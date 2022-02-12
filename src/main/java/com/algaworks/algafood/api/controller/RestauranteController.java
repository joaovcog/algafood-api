package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long codigo) {
		Restaurante restaurante = restauranteRepository.buscar(codigo);

		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<?> atualizar(@PathVariable Long codigo, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteExistente = restauranteRepository.buscar(codigo);

			if (restauranteExistente != null) {
				BeanUtils.copyProperties(restaurante, restauranteExistente, "codigo");

				restauranteExistente = restauranteService.salvar(restauranteExistente);

				return ResponseEntity.ok(restauranteExistente);
			}

			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{codigo}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long codigo, @RequestBody Map<String, Object> campos) {
		Restaurante restauranteExistente = restauranteRepository.buscar(codigo);
		
		if (restauranteExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteExistente);
		
		return atualizar(codigo, restauranteExistente);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		System.out.println(restauranteOrigem);
		
		dadosOrigem.forEach((chave, valor) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, chave);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			System.out.println(chave + " = " + valor + " = " + novoValor);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
