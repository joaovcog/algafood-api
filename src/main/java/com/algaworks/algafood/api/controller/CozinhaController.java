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
import com.algaworks.algafood.api.dto.input.CozinhaInputDto;
import com.algaworks.algafood.api.dto.output.CozinhaOutputDto;
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

	@GetMapping
	public List<CozinhaOutputDto> listar() {
		return cozinhaDtoAssembler.toCollectionOutputDtoFromDomainEntity(cozinhaRepository.findAll(), CozinhaOutputDto.class);
	}

	@GetMapping("/{codigo}")
	public CozinhaOutputDto buscar(@PathVariable Long codigo) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(codigo);
		
		return cozinhaDtoAssembler.toOutputDtoFromDomainEntity(cozinha, CozinhaOutputDto.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaOutputDto adicionar(@RequestBody @Valid CozinhaInputDto cozinhaInput) { // o RequestBody vincula o corpo da requisição no parâmetro cozinha
		Cozinha cozinha = cozinhaDtoAssembler.toDomainObjectFromInputDto(cozinhaInput, Cozinha.class);
		
		return cozinhaDtoAssembler.toOutputDtoFromDomainEntity(cozinhaService.salvar(cozinha), CozinhaOutputDto.class);
	}

	@PutMapping("/{codigo}")
	public CozinhaOutputDto atualizar(@PathVariable Long codigo, @RequestBody @Valid CozinhaInputDto cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(codigo);
		
		cozinhaDtoAssembler.copyFromInputDtoToDomainObject(cozinhaInput, cozinhaAtual);

		return cozinhaDtoAssembler.toOutputDtoFromDomainEntity(cozinhaService.salvar(cozinhaAtual), CozinhaOutputDto.class);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cozinhaService.excluir(codigo);
	}

}
