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

import com.algaworks.algafood.api.assembler.GrupoDtoAssembler;
import com.algaworks.algafood.api.dto.output.GrupoOutputDto;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{codUsuario}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoDtoAssembler grupoDtoAssembler;
	
	@GetMapping
	public List<GrupoOutputDto> listar(@PathVariable Long codUsuario) {
		Usuario usuario = usuarioService.buscarOuFalhar(codUsuario);
		
		return grupoDtoAssembler.toCollectionOutputDtoFromDomainEntity(usuario.getGrupos());
	}

	@PutMapping("/{codGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vincularGrupo(@PathVariable Long codUsuario, @PathVariable Long codGrupo) {
		usuarioService.vincularGrupo(codUsuario, codGrupo);
	}
	
	@DeleteMapping("/{codGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desvincularGrupo(@PathVariable Long codUsuario, @PathVariable Long codGrupo) {
		usuarioService.desvincularGrupo(codUsuario, codGrupo);
	}
	
}
