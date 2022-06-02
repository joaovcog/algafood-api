package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.assembler.impl.representation.CozinhaDtoRepresentationAssembler;
import com.algaworks.algafood.api.dto.input.CozinhaInputDto;
import com.algaworks.algafood.api.dto.output.CozinhaOutputDto;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

import io.swagger.annotations.Api;

@Api(tags = "Cozinhas")
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaDtoRepresentationAssembler cozinhaDtoRepresentationAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<CozinhaOutputDto> listar(@PageableDefault(size = 2) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaOutputDto> cozinhasPagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaDtoRepresentationAssembler);
		
		return cozinhasPagedModel;
	}

	@GetMapping("/{codCozinha}")
	public CozinhaOutputDto buscar(@PathVariable Long codCozinha) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(codCozinha);
		
		return cozinhaDtoRepresentationAssembler.toModel(cozinha);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaOutputDto adicionar(@RequestBody @Valid CozinhaInputDto cozinhaInput) { // o RequestBody vincula o corpo da requisição no parâmetro cozinhaInput
		Cozinha cozinha = cozinhaDtoRepresentationAssembler.toDomainObjectFromInputDto(cozinhaInput);
		cozinha = cozinhaService.salvar(cozinha);
		
		return cozinhaDtoRepresentationAssembler.toModel(cozinha);
	}

	@PutMapping("/{codCozinha}")
	public CozinhaOutputDto atualizar(@PathVariable Long codCozinha, @RequestBody @Valid CozinhaInputDto cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(codCozinha);
		
		cozinhaDtoRepresentationAssembler.copyFromInputDtoToDomainObject(cozinhaInput, cozinhaAtual);
		
		cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

		return cozinhaDtoRepresentationAssembler.toModel(cozinhaAtual);
	}

	@DeleteMapping("/{codCozinha}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codCozinha) {
		cozinhaService.excluir(codCozinha);
	}

}
