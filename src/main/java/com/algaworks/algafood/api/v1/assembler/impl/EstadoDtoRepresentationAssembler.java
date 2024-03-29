package com.algaworks.algafood.api.v1.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.v1.controller.EstadoController;
import com.algaworks.algafood.api.v1.dto.input.EstadoInputDto;
import com.algaworks.algafood.api.v1.dto.output.EstadoOutputDto;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Estado, EstadoInputDto, EstadoOutputDto, EstadoController> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoDtoRepresentationAssembler() {
		super(EstadoController.class, EstadoOutputDto.class);
	}

	@Override
	public EstadoOutputDto toModel(Estado entity) {
		EstadoOutputDto estadoOutputDto = createModelWithId(entity.getCodigo(), entity);
		modelMapper.map(entity, estadoOutputDto);
		
//		estadoOutputDto.add(linkTo(methodOn(getControllerClass())
//				.buscar(estadoOutputDto.getCodigo())).withSelfRel());
		
		estadoOutputDto.add(linkTo(getControllerClass())
				.withRel("estados"));
		
		return estadoOutputDto;
	}
	
}
