package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.impl.representation.CidadeDtoRepresentationAssembler;
import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeDtoRepresentationAssembler cidadeDtoRepresentationAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cidade> pagedResourcesAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<CidadeOutputDto> listar(@PageableDefault(size = 5) Pageable pageable) {
		Page<Cidade> cidadesPage = cidadeRepository.findAllPaginado(pageable);
		
		PagedModel<CidadeOutputDto> cidadesPagedModel = pagedResourcesAssembler.toModel(cidadesPage, cidadeDtoRepresentationAssembler);
		
		return cidadesPagedModel;
	}

	@GetMapping(path = "/{codCidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeOutputDto buscar(@PathVariable Long codCidade) {
		Cidade cidade = cidadeService.buscarOuFalhar(codCidade);
		
		CidadeOutputDto cidadeOutputDto = cidadeDtoRepresentationAssembler.toModel(cidade);
		
		return cidadeOutputDto;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeOutputDto adicionar(@RequestBody @Valid CidadeInputDto cidadeInput) {
		try {
			Cidade cidade = cidadeDtoRepresentationAssembler.toDomainObjectFromInputDto(cidadeInput);
			
			CidadeOutputDto cidadeOutputDto = cidadeDtoRepresentationAssembler.toModel(cidadeService.salvar(cidade));
			
			ResourceUriHelper.addUriToResponseHeader(cidadeOutputDto.getCodigo());
			
			return cidadeOutputDto;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping(path = "/{codCidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeOutputDto atualizar(@PathVariable Long codCidade, @RequestBody @Valid CidadeInputDto cidadeInput) {
		Cidade cidadeAtual = cidadeService.buscarOuFalhar(codCidade);
		
		cidadeDtoRepresentationAssembler.copyFromInputDtoToDomainObject(cidadeInput, cidadeAtual);

		try {
			return cidadeDtoRepresentationAssembler.toModel(cidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{codCidade}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codCidade) {
		cidadeService.excluir(codCidade);
	}
	
}
