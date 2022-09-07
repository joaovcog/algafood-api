package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.UsuarioInputDto;
import com.algaworks.algafood.api.v1.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoAssembler extends GenericInputOutputAssembler<Usuario, UsuarioInputDto, UsuarioOutputDto> {

}
