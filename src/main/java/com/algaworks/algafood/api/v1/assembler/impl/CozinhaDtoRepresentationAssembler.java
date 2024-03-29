package com.algaworks.algafood.api.v1.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.dto.input.CozinhaInputDto;
import com.algaworks.algafood.api.v1.dto.output.CozinhaOutputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Cozinha, CozinhaInputDto, CozinhaOutputDto, CozinhaController> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaDtoRepresentationAssembler() {
		super(CozinhaController.class, CozinhaOutputDto.class);
	}

	@Override
	public CozinhaOutputDto toModel(Cozinha entity) {
		CozinhaOutputDto cozinhaOutputDto = createModelWithId(entity.getCodigo(), entity);
		modelMapper.map(entity, cozinhaOutputDto);
		
		cozinhaOutputDto.add(linkTo(getControllerClass())
				.withRel("cozinhas"));
		
		return cozinhaOutputDto;
	}
	
}
