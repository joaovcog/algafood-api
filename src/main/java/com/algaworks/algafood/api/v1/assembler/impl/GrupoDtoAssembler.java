package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.v1.dto.output.GrupoOutputDto;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoDtoAssembler extends GenericInputOutputAssembler<Grupo, GrupoInputDto, GrupoOutputDto> {

}
