package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.UsuarioInputDto;
import com.algaworks.algafood.api.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioDtoAssembler extends GenericAssembler<Usuario, UsuarioInputDto, UsuarioOutputDto> {

}
