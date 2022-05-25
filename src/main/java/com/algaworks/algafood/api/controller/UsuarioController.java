package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.impl.UsuarioDtoRepresentationAssembler;
import com.algaworks.algafood.api.dto.input.UsuarioAtualizacaoInputDto;
import com.algaworks.algafood.api.dto.input.UsuarioAtualizacaoSenhaInputDto;
import com.algaworks.algafood.api.dto.input.UsuarioCadastroInputDto;
import com.algaworks.algafood.api.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDtoRepresentationAssembler usuarioDtoRepresentationAssembler;
	
	@GetMapping
	public CollectionModel<UsuarioOutputDto> listar() {
		List<Usuario> usuarios = usuarioService.listar();
		
		return usuarioDtoRepresentationAssembler.toCollectionModel(usuarios);
	}
	
	@GetMapping("/{codUsuario}")
	public UsuarioOutputDto buscar(@PathVariable Long codUsuario) {
		Usuario usuario = usuarioService.buscarOuFalhar(codUsuario);
		
		return usuarioDtoRepresentationAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioOutputDto adicionar(@RequestBody @Valid UsuarioCadastroInputDto usuarioInput) {
		Usuario usuario = usuarioDtoRepresentationAssembler.toDomainObjectFromInputDto(usuarioInput);
		usuario = usuarioService.salvar(usuario);
		
		return usuarioDtoRepresentationAssembler.toModel(usuario);
	}
	
	@PutMapping("/{codUsuario}")
	public UsuarioOutputDto atualizar(@PathVariable Long codUsuario, @RequestBody @Valid UsuarioAtualizacaoInputDto usuarioInput) {
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(codUsuario);
		
		usuarioDtoRepresentationAssembler.copyFromInputDtoToDomainObject(usuarioInput, usuarioAtual);
		
		usuarioAtual = usuarioService.salvar(usuarioAtual);
		
		return usuarioDtoRepresentationAssembler.toModel(usuarioAtual);
	}
	
	@PutMapping("/{codUsuario}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long codUsuario, @RequestBody @Valid UsuarioAtualizacaoSenhaInputDto usuarioInput) {
		usuarioService.atualizarSenha(codUsuario, usuarioInput.getSenhaAtual(), usuarioInput.getNovaSenha());
	}
	
}
