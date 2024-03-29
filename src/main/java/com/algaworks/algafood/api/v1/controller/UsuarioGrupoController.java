package com.algaworks.algafood.api.v1.controller;

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

import com.algaworks.algafood.api.v1.assembler.impl.GrupoDtoAssembler;
import com.algaworks.algafood.api.v1.dto.output.GrupoOutputDto;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios/{codUsuario}/grupos")
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoDtoAssembler grupoDtoAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public List<GrupoOutputDto> listar(@PathVariable Long codUsuario) {
		Usuario usuario = usuarioService.buscarOuFalhar(codUsuario);
		
		return grupoDtoAssembler.toCollectionOutputDtoFromDomainEntity(usuario.getGrupos());
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{codGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vincularGrupo(@PathVariable Long codUsuario, @PathVariable Long codGrupo) {
		usuarioService.vincularGrupo(codUsuario, codGrupo);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{codGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desvincularGrupo(@PathVariable Long codUsuario, @PathVariable Long codGrupo) {
		usuarioService.desvincularGrupo(codUsuario, codGrupo);
	}
	
}
