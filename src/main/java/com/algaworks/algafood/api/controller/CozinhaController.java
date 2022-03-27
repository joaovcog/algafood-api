package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public Cozinha buscar(@PathVariable Long codigo) {
		return cozinhaService.buscarOuFalhar(codigo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) { // o RequestBody vincula o corpo da requisição no parâmetro cozinha
		return cozinhaService.salvar(cozinha);
	}

	@PutMapping("/{codigo}")
	public Cozinha atualizar(@PathVariable Long codigo, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(codigo);

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "codigo");

		return cozinhaService.salvar(cozinhaAtual);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cozinhaService.excluir(codigo);
	}

}
