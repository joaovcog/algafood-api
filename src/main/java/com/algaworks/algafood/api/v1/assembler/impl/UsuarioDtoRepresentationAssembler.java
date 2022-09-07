package com.algaworks.algafood.api.v1.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.v1.controller.UsuarioController;
import com.algaworks.algafood.api.v1.controller.UsuarioGrupoController;
import com.algaworks.algafood.api.v1.dto.input.UsuarioInputDto;
import com.algaworks.algafood.api.v1.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Usuario, UsuarioInputDto, UsuarioOutputDto, UsuarioController> {
	
	private final Class<UsuarioController> controllerClass;
	
	public UsuarioDtoRepresentationAssembler() {
		super(UsuarioController.class, UsuarioOutputDto.class);
		
		this.controllerClass = UsuarioController.class;
	}

	@Override
	public UsuarioOutputDto toModel(Usuario entity) {
		UsuarioOutputDto usuarioOutputDto = toOutputDtoFromDomainEntity(entity);
		
		usuarioOutputDto.add(linkTo(methodOn(controllerClass)
				.buscar(usuarioOutputDto.getCodigo()))
				.withSelfRel());
		
		usuarioOutputDto.add(linkTo(controllerClass)
				.withRel("usuarios"));
		
		usuarioOutputDto.add(linkTo(methodOn(UsuarioGrupoController.class)
				.listar(usuarioOutputDto.getCodigo()))
				.withRel("grupos-usuario"));
		
		return usuarioOutputDto;
	}
	
}
