package com.algaworks.algafood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.impl.CidadeDtoRepresentationAssemblerV2;
import com.algaworks.algafood.api.v2.dto.input.CidadeInputDtoV2;
import com.algaworks.algafood.api.v2.dto.output.CidadeOutputDtoV2;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.core.web.AlgaMediaTypes;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeControllerV2 {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeDtoRepresentationAssemblerV2 cidadeDtoRepresentationAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Cidade> pagedResourcesAssembler;
	
	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public PagedModel<CidadeOutputDtoV2> listar(@PageableDefault(size = 5) Pageable pageable) {
		Page<Cidade> cidadesPage = cidadeRepository.findAllPaginado(pageable);
		
		PagedModel<CidadeOutputDtoV2> cidadesPagedModel = pagedResourcesAssembler.toModel(cidadesPage, cidadeDtoRepresentationAssembler);
		
		return cidadesPagedModel;
	}
	
	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping(path = "/{codCidade}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CidadeOutputDtoV2 buscar(@PathVariable Long codCidade) {
		Cidade cidade = cidadeService.buscarOuFalhar(codCidade);
		
		CidadeOutputDtoV2 cidadeOutputDto = cidadeDtoRepresentationAssembler.toModel(cidade);
		
		return cidadeOutputDto;
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@PostMapping(produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeOutputDtoV2 adicionar(@RequestBody @Valid CidadeInputDtoV2 cidadeInput) {
		try {
			Cidade cidade = cidadeDtoRepresentationAssembler.toDomainObjectFromInputDto(cidadeInput);
			
			CidadeOutputDtoV2 cidadeOutputDto = cidadeDtoRepresentationAssembler.toModel(cidadeService.salvar(cidade));
			
			ResourceUriHelper.addUriToResponseHeader(cidadeOutputDto.getCodigoCidade());
			
			return cidadeOutputDto;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@PutMapping(path = "/{codCidade}", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
	public CidadeOutputDtoV2 atualizar(@PathVariable Long codCidade, @RequestBody @Valid CidadeInputDtoV2 cidadeInput) {
		Cidade cidadeAtual = cidadeService.buscarOuFalhar(codCidade);
		
		cidadeDtoRepresentationAssembler.copyFromInputDtoToDomainObject(cidadeInput, cidadeAtual);

		try {
			return cidadeDtoRepresentationAssembler.toModel(cidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
}
