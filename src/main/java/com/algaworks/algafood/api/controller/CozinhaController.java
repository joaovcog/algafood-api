package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long codigo) {
		Cozinha cozinha = cozinhaRepository.buscar(codigo);

		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) { // o RequestBody vincula o corpo da requisição no parâmetro
																// cozinha
		return cozinhaService.salvar(cozinha);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long codigo, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaExistente = cozinhaRepository.buscar(codigo);

		if (cozinhaExistente != null) {
			BeanUtils.copyProperties(cozinha, cozinhaExistente, "codigo");

			cozinhaExistente = cozinhaService.salvar(cozinhaExistente);

			return ResponseEntity.ok(cozinhaExistente);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long codigo) {
		try {
			cozinhaService.excluir(codigo);

			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
