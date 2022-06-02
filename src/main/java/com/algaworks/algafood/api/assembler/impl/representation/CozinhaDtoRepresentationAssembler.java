package com.algaworks.algafood.api.assembler.impl.representation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.dto.input.CozinhaInputDto;
import com.algaworks.algafood.api.dto.output.CozinhaOutputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Cozinha, CozinhaInputDto, CozinhaOutputDto, CozinhaController> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public CozinhaDtoRepresentationAssembler() {
		super(CozinhaController.class, CozinhaOutputDto.class);
	}

	@Override
	public CozinhaOutputDto toModel(Cozinha entity) {
		CozinhaOutputDto cozinhaOutputDto = createModelWithId(entity.getCodigo(), entity);
		modelMapper.map(entity, cozinhaOutputDto);
		
		cozinhaOutputDto.add(algaLinks.linkToCozinhas("cozinhas"));
		
		return cozinhaOutputDto;
	}
	
}
