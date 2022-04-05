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

import com.algaworks.algafood.api.assembler.EstadoDtoAssembler;
import com.algaworks.algafood.api.assembler.EstadoDtoDisassembler;
import com.algaworks.algafood.api.model.EstadoOutputDto;
import com.algaworks.algafood.api.model.input.EstadoInputDto;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoDtoAssembler estadoDtoAssembler;
	
	@Autowired
	private EstadoDtoDisassembler estadoDtoDisassembler;

	@GetMapping
	public List<EstadoOutputDto> listar() {
		return estadoDtoAssembler.toCollectionOutputDtoFromModel(estadoRepository.findAll());
	}

	@GetMapping("/{codigo}")
	public EstadoOutputDto buscar(@PathVariable Long codigo) {
		Estado estado = estadoService.buscarOuFalhar(codigo);
		
		return estadoDtoAssembler.toOutputDtoFromModel(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoOutputDto adicionar(@RequestBody @Valid EstadoInputDto estadoInput) {
		Estado estado = estadoDtoDisassembler.toDomainObjectFromInputDto(estadoInput);
		
		return estadoDtoAssembler.toOutputDtoFromModel(estadoService.salvar(estado));
	}

	@PutMapping("/{codigo}")
	public EstadoOutputDto atualizar(@PathVariable Long codigo, @RequestBody @Valid EstadoInputDto estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(codigo);
		
		estadoDtoDisassembler.copyFromInputDtoToDomainObject(estadoInput, estadoAtual);

		return estadoDtoAssembler.toOutputDtoFromModel(estadoService.salvar(estadoAtual));
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		estadoService.excluir(codigo);
	}
}
