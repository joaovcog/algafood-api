package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PermissaoDtoAssembler;
import com.algaworks.algafood.api.dto.output.PermissaoOutputDto;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos/{codGrupo}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoDtoAssembler permissaoDtoAssembler;
	
	@GetMapping
	public List<PermissaoOutputDto> listar(@PathVariable Long codGrupo) {
		Grupo grupo = grupoService.buscarOuFalhar(codGrupo);
		
		return permissaoDtoAssembler.toCollectionOutputDtoFromDomainEntity(grupo.getPermissoes());
	}
	
	@PutMapping("/{codPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vincularPermissao(@PathVariable Long codGrupo, @PathVariable Long codPermissao) {
		grupoService.vincularPermissao(codGrupo, codPermissao);
	}
	
	@DeleteMapping("/{codPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desvincularPermissao(@PathVariable Long codGrupo, @PathVariable Long codPermissao) {
		grupoService.desvincularPermissao(codGrupo, codPermissao);
	}
	

}
