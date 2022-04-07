package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.GrupoDtoAssembler;
import com.algaworks.algafood.api.dto.output.GrupoOutputDto;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoDtoAssembler grupoDtoAssembler;
	
	@GetMapping
	public List<GrupoOutputDto> listar() {
		List<Grupo> grupos = grupoService.listar();
		
		return grupoDtoAssembler.toCollectionOutputDtoFromDomainEntity(grupos, GrupoOutputDto.class);
	}
	
	@GetMapping("/{codigo}")
	public GrupoOutputDto buscar(@PathVariable Long codigo) {
		Grupo grupo = grupoService.buscarOuFalhar(codigo);
		
		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupo, GrupoOutputDto.class);
	}
	
}
