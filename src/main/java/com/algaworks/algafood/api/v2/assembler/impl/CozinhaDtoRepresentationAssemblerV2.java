package com.algaworks.algafood.api.v2.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.assembler.generic.GenericInputOutputRepresentationAssemblerV2;
import com.algaworks.algafood.api.v2.controller.CozinhaControllerV2;
import com.algaworks.algafood.api.v2.dto.input.CozinhaInputDtoV2;
import com.algaworks.algafood.api.v2.dto.output.CozinhaOutputDtoV2;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoRepresentationAssemblerV2 extends GenericInputOutputRepresentationAssemblerV2<Cozinha, CozinhaInputDtoV2, CozinhaOutputDtoV2, CozinhaControllerV2> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaDtoRepresentationAssemblerV2() {
		super(CozinhaControllerV2.class, CozinhaOutputDtoV2.class);
	}

	@Override
	public CozinhaOutputDtoV2 toModel(Cozinha entity) {
		CozinhaOutputDtoV2 cozinhaOutputDto = createModelWithId(entity.getCodigo(), entity);
		modelMapper.map(entity, cozinhaOutputDto);
		
		cozinhaOutputDto.add(linkTo(getControllerClass())
				.withRel("cozinhas"));
		
		return cozinhaOutputDto;
	}
	
}
