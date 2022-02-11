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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.listar();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long codigo) {
		Cidade cidade = cidadeRepository.buscar(codigo);
		
		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {
			cidade = cidadeService.salvar(cidade);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long codigo, @RequestBody Cidade cidade) {
		Cidade cidadeExistente = cidadeRepository.buscar(codigo);
		
		if (cidadeExistente != null) {
			BeanUtils.copyProperties(cidade, cidadeExistente, "codigo");
			
			cidadeExistente = cidadeService.salvar(cidadeExistente);
			
			return ResponseEntity.ok(cidadeExistente);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable Long codigo) {
		try {
			cidadeService.excluir(codigo);
			
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
