package com.algaworks.algafood.api.v2.controller;

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

import com.algaworks.algafood.api.v2.assembler.impl.CozinhaDtoRepresentationAssemblerV2;
import com.algaworks.algafood.api.v2.dto.input.CozinhaInputDtoV2;
import com.algaworks.algafood.api.v2.dto.output.CozinhaOutputDtoV2;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("/v2/cozinhas")
public class CozinhaControllerV2 {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	@Autowired
	private CozinhaDtoRepresentationAssemblerV2 cozinhaDtoRepresentationAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping
	public PagedModel<CozinhaOutputDtoV2> listar(@PageableDefault(size = 2) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		PagedModel<CozinhaOutputDtoV2> cozinhasPagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaDtoRepresentationAssembler);
		
		return cozinhasPagedModel;
	}

	@CheckSecurity.Cozinhas.PodeConsultar
	@GetMapping("/{codCozinha}")
	public CozinhaOutputDtoV2 buscar(@PathVariable Long codCozinha) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(codCozinha);
		
		return cozinhaDtoRepresentationAssembler.toModel(cozinha);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaOutputDtoV2 adicionar(@RequestBody @Valid CozinhaInputDtoV2 cozinhaInput) { // o RequestBody vincula o corpo da requisição no parâmetro cozinhaInput
		Cozinha cozinha = cozinhaDtoRepresentationAssembler.toDomainObjectFromInputDto(cozinhaInput);
		cozinha = cozinhaService.salvar(cozinha);
		
		return cozinhaDtoRepresentationAssembler.toModel(cozinha);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@PutMapping("/{codCozinha}")
	public CozinhaOutputDtoV2 atualizar(@PathVariable Long codCozinha, @RequestBody @Valid CozinhaInputDtoV2 cozinhaInput) {
		Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(codCozinha);
		
		cozinhaDtoRepresentationAssembler.copyFromInputDtoToDomainObject(cozinhaInput, cozinhaAtual);
		
		cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

		return cozinhaDtoRepresentationAssembler.toModel(cozinhaAtual);
	}

	@CheckSecurity.Cozinhas.PodeEditar
	@DeleteMapping("/{codCozinha}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codCozinha) {
		cozinhaService.excluir(codCozinha);
	}

}
