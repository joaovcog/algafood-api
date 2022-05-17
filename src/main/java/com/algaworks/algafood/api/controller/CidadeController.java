package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.algaworks.algafood.api.assembler.impl.CidadeDtoAssembler;
import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeDtoAssembler cidadeDtoAssembler;
	
	@ApiOperation("Lista as cidades")
	@GetMapping
	public Page<CidadeOutputDto> listar(@PageableDefault(size = 5) Pageable pageable) {
		Page<Cidade> cidadesPage = cidadeRepository.findAllPaginado(pageable);
		
		List<CidadeOutputDto> cidadesOutputDto = cidadeDtoAssembler
				.toCollectionOutputDtoFromDomainEntity(cidadesPage.getContent());
		
		Page<CidadeOutputDto> cidadesOutputDtoPage = new PageImpl<>(cidadesOutputDto, pageable, cidadesPage.getTotalElements());
		
		return cidadesOutputDtoPage;
	}

	@ApiOperation("Busca uma cidade por código")
	@GetMapping("/{codCidade}")
	public CidadeOutputDto buscar(@ApiParam(value = "Código de uma cidade") @PathVariable Long codCidade) {
		Cidade cidade = cidadeService.buscarOuFalhar(codCidade);
		
		return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidade);
	}

	@ApiOperation("Cadastra uma cidade")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeOutputDto adicionar(@RequestBody @Valid CidadeInputDto cidadeInput) {
		try {
			Cidade cidade = cidadeDtoAssembler.toDomainObjectFromInputDto(cidadeInput);
			
			return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidadeService.salvar(cidade));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Atualiza uma cidade por código")
	@PutMapping("/{codCidade}")
	public CidadeOutputDto atualizar(@PathVariable Long codCidade, @RequestBody @Valid CidadeInputDto cidadeInput) {
		Cidade cidadeAtual = cidadeService.buscarOuFalhar(codCidade);
		
		cidadeDtoAssembler.copyFromInputDtoToDomainObject(cidadeInput, cidadeAtual);

		try {
			return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Exclui uma cidade por código")
	@DeleteMapping("/{codCidade}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "Código de uma cidade") @PathVariable Long codCidade) {
		cidadeService.excluir(codCidade);
	}
	
}
