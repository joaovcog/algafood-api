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
import com.algaworks.algafood.api.model.CidadeOutputDto;
import com.algaworks.algafood.api.model.input.CidadeInputDto;
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
		return cidadeDtoAssembler.toCollectionOutputDtoFromDomainEntity(cidadeRepository.findAll(), CidadeOutputDto.class);
	}

	@GetMapping("/{codigo}")
	public CidadeOutputDto buscar(@PathVariable Long codigo) {
		Cidade cidade = cidadeService.buscarOuFalhar(codigo);
		
		return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidade, CidadeOutputDto.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeOutputDto adicionar(@RequestBody @Valid CidadeInputDto cidadeInput) {
		try {
			Cidade cidade = cidadeDtoAssembler.toDomainObjectFromInputDto(cidadeInput, Cidade.class);
			
			return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidadeService.salvar(cidade), CidadeOutputDto.class);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{codigo}")
	public CidadeOutputDto atualizar(@PathVariable Long codigo, @RequestBody @Valid CidadeInputDto cidadeInput) {
		Cidade cidadeAtual = cidadeService.buscarOuFalhar(codigo);
		
		cidadeDtoAssembler.copyFromInputDtoToDomainObject(cidadeInput, cidadeAtual);

		try {
			return cidadeDtoAssembler.toOutputDtoFromDomainEntity(cidadeService.salvar(cidadeAtual), CidadeOutputDto.class);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		cidadeService.excluir(codigo);
	}
	
}
