package com.algaworks.algafood.api.assembler.impl.representation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.dto.input.UsuarioInputDto;
import com.algaworks.algafood.api.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Usuario, UsuarioInputDto, UsuarioOutputDto, UsuarioController> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public UsuarioDtoRepresentationAssembler() {
		super(UsuarioController.class, UsuarioOutputDto.class);
	}

	@Override
	public UsuarioOutputDto toModel(Usuario entity) {
		UsuarioOutputDto usuarioOutputDto = toOutputDtoFromDomainEntity(entity);
		
		usuarioOutputDto.add(algaLinks.linkToUsuarioCliente(usuarioOutputDto.getCodigo()));
		
		usuarioOutputDto.add(algaLinks.linkToUsuariosClientes("usuarios"));
		
		usuarioOutputDto.add(algaLinks.linkToGruposUsuario(usuarioOutputDto.getCodigo(), "grupos-usuario"));
		
		return usuarioOutputDto;
	}
	
}
