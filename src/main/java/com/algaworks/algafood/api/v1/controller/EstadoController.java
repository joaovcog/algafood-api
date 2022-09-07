package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.algaworks.algafood.api.v1.assembler.impl.EstadoDtoRepresentationAssembler;
import com.algaworks.algafood.api.v1.dto.input.EstadoInputDto;
import com.algaworks.algafood.api.v1.dto.output.EstadoOutputDto;
import com.algaworks.algafood.core.security.CheckSecurity;
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
	private EstadoDtoRepresentationAssembler estadoDtoRepresentationAssembler;
	
	@CheckSecurity.Estados.PodeConsultar
	@GetMapping
	public CollectionModel<EstadoOutputDto> listar() {
		List<Estado> estados = estadoRepository.findAll();
		
		return estadoDtoRepresentationAssembler.toCollectionModel(estados);
	}
	
	@CheckSecurity.Estados.PodeConsultar
	@GetMapping("/{codEstado}")
	public EstadoOutputDto buscar(@PathVariable Long codEstado) {
		Estado estado = estadoService.buscarOuFalhar(codEstado);
		
		return estadoDtoRepresentationAssembler.toModel(estado);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoOutputDto adicionar(@RequestBody @Valid EstadoInputDto estadoInput) {
		Estado estado = estadoDtoRepresentationAssembler.toDomainObjectFromInputDto(estadoInput);
		estado = estadoService.salvar(estado);
		
		return estadoDtoRepresentationAssembler.toModel(estado);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@PutMapping("/{codEstado}")
	public EstadoOutputDto atualizar(@PathVariable Long codEstado, @RequestBody @Valid EstadoInputDto estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(codEstado);
		
		estadoDtoRepresentationAssembler.copyFromInputDtoToDomainObject(estadoInput, estadoAtual);
		
		estadoAtual = estadoService.salvar(estadoAtual);

		return estadoDtoRepresentationAssembler.toModel(estadoAtual);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@DeleteMapping("/{codEstado}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codEstado) {
		estadoService.excluir(codEstado);
	}
}
