package com.algaworks.algafood.api.v2.assembler.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v2.assembler.generic.GenericInputOutputRepresentationAssemblerV2;
import com.algaworks.algafood.api.v2.controller.CidadeControllerV2;
import com.algaworks.algafood.api.v2.dto.input.CidadeInputDtoV2;
import com.algaworks.algafood.api.v2.dto.output.CidadeOutputDtoV2;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDtoRepresentationAssemblerV2 extends GenericInputOutputRepresentationAssemblerV2<Cidade, CidadeInputDtoV2, CidadeOutputDtoV2, CidadeControllerV2> {
	
	public CidadeDtoRepresentationAssemblerV2() {
		super(CidadeControllerV2.class, CidadeOutputDtoV2.class);
	}

	@Override
	public void copyFromInputDtoToDomainObject(CidadeInputDtoV2 inputDto, Cidade entity) {
		entity.setEstado(new Estado());
		
		super.copyFromInputDtoToDomainObject(inputDto, entity);
	}

	@Override
	public CidadeOutputDtoV2 toModel(Cidade entity) {
		CidadeOutputDtoV2 cidadeOutputDto = toOutputDtoFromDomainEntity(entity);
		
		cidadeOutputDto.add(linkTo(methodOn(CidadeControllerV2.class)
				.buscar(cidadeOutputDto.getCodigoCidade())).withSelfRel());
		
		cidadeOutputDto.add(linkTo(CidadeControllerV2.class)
				.withRel("cidades"));
		
		return cidadeOutputDto;
	}
	
}
