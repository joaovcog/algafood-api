package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.dto.output.GrupoOutputDto;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoDtoAssembler extends GenericAssembler<Grupo, GrupoInputDto, GrupoOutputDto> {

}
