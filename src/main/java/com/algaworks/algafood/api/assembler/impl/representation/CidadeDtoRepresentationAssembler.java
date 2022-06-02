package com.algaworks.algafood.api.assembler.impl.representation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Cidade, CidadeInputDto, CidadeOutputDto, CidadeController> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
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
		
		cidadeOutputDto.add(algaLinks.linkToCidade(cidadeOutputDto.getCodigo()));
		
		cidadeOutputDto.add(algaLinks.linkToCidades("cidades"));
		
		cidadeOutputDto.getEstado().add(algaLinks.linkToEstado(cidadeOutputDto.getEstado().getCodigo()));
		
		return cidadeOutputDto;
	}
	
}
