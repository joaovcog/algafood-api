package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CozinhaDtoAssembler;
import com.algaworks.algafood.api.assembler.CozinhaDtoDisassembler;
import com.algaworks.algafood.api.model.CozinhaOutputDto;
import com.algaworks.algafood.api.model.input.CozinhaInputDto;
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
	
	@Autowired
	private CozinhaDtoAssembler cozinhaDtoAssembler;
	
	@Autowired
	private CozinhaDtoDisassembler cozinhaDtoDisassembler;

	@GetMapping
	public List<CozinhaOutputDto> listar() {
		return cozinhaDtoAssembler.toCollectionOutputDtoFromModel(cozinhaRepository.findAll());
	}

	@GetMapping("/{codigo}")
	public CozinhaOutputDto buscar(@PathVariable Long codigo) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(codigo);
		
		return cozinhaDtoAssembler.toOutputDtoFromModel(cozinha);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaOutputDto adicionar(@RequestBody @Valid CozinhaInputDto cozinhaInput) { // o RequestBody vincula o corpo da requisição no parâmetro cozinha
		Cozinha cozinha = cozinhaDtoDisassembler.toDomainObjectFromInputDto(cozinhaInput);
		
		return cozinhaDtoAssembler.toOutputDtoFromModel(cozinhaService.salvar(cozinha));
	}

	@PutMapping("/{codigo}")
	public CozinhaOutputDto atualizar(@PathVariable Long codigo, @RequestBody @Valid CozinhaInputDto cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(codigo);
		
		cozinhaDtoDisassembler.copyFromInputDtoToDomainObject(cozinhaInput, cozinhaAtual);

		return cozinhaDtoAssembler.toOutputDtoFromModel(cozinhaService.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cozinhaService.excluir(codigo);
	}

}
