package com.algaworks.algafood.api.assembler.impl.representation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Cidade, CidadeInputDto, CidadeOutputDto, CidadeController> {
	
	public CidadeDtoRepresentationAssembler() {
		super(CidadeController.class, CidadeOutputDto.class);
	}

	@Override
	public void copyFromInputDtoToDomainObject(CidadeInputDto inputDto, Cidade entity) {
		entity.setEstado(new Estado());
		
		super.copyFromInputDtoToDomainObject(inputDto, entity);
	}

	@Override
	public CidadeOutputDto toModel(Cidade entity) {
		CidadeOutputDto cidadeOutputDto = toOutputDtoFromDomainEntity(entity);
		
		cidadeOutputDto.add(linkTo(methodOn(CidadeController.class)
				.buscar(cidadeOutputDto.getCodigo())).withSelfRel());
		
		cidadeOutputDto.add(linkTo(CidadeController.class)
				.withRel("cidades"));
		
		cidadeOutputDto.getEstado().add(linkTo(methodOn(EstadoController.class)
				.buscar(cidadeOutputDto.getEstado().getCodigo())).withSelfRel());
		
		return cidadeOutputDto;
	}
	
}
