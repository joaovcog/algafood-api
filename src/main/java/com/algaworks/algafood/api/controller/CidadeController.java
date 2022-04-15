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

import com.algaworks.algafood.api.assembler.CidadeDtoAssembler;
import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
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
	
	@Autowired
	private CidadeDtoAssembler cidadeDtoAssembler;
	
	@GetMapping
	public List<CidadeOutputDto> listar() {
		return cidadeDtoAssembler.toCollectionOutputDtoFromDomainEntity(cidadeRepository.findAll());
	}

	@GetMapping("/{codCidade}")
	public CidadeOutputDto buscar(@PathVariable Long codCidade) {
		Cidade cidade = cidadeService.buscarOuFalhar(codCidade);
		
		return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidade);
	}

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

	@DeleteMapping("/{codCidade}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codCidade) {
		cidadeService.excluir(codCidade);
	}
	
}
