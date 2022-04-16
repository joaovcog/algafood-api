package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.UsuarioDtoAssembler;
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
	private UsuarioDtoAssembler usuarioDtoAssembler;
	
	@GetMapping
	public List<UsuarioOutputDto> listar() {
		List<Usuario> usuarios = usuarioService.listar();
		
		return usuarioDtoAssembler.toCollectionOutputDtoFromDomainEntity(usuarios);
	}
	
	@GetMapping("/{codUsuario}")
	public UsuarioOutputDto buscar(@PathVariable Long codUsuario) {
		Usuario usuario = usuarioService.buscarOuFalhar(codUsuario);
		
		return usuarioDtoAssembler.toOutputDtoFromDomainEntity(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioOutputDto adicionar(@RequestBody @Valid UsuarioCadastroInputDto usuarioInput) {
		Usuario usuario = usuarioDtoAssembler.toDomainObjectFromInputDto(usuarioInput);
		usuario = usuarioService.salvar(usuario);
		
		return usuarioDtoAssembler.toOutputDtoFromDomainEntity(usuario);
	}
	
	@PutMapping("/{codUsuario}")
	public UsuarioOutputDto atualizar(@PathVariable Long codUsuario, @RequestBody @Valid UsuarioAtualizacaoInputDto usuarioInput) {
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(codUsuario);
		
		usuarioDtoAssembler.copyFromInputDtoToDomainObject(usuarioInput, usuarioAtual);
		
		usuarioAtual = usuarioService.salvar(usuarioAtual);
		
		return usuarioDtoAssembler.toOutputDtoFromDomainEntity(usuarioAtual);
	}
	
	@PutMapping("/{codUsuario}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long codUsuario, @RequestBody @Valid UsuarioAtualizacaoSenhaInputDto usuarioInput) {
		usuarioService.atualizarSenha(codUsuario, usuarioInput.getSenhaAtual(), usuarioInput.getNovaSenha());
	}
	
}