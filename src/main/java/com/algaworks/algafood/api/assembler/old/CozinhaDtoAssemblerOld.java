package com.algaworks.algafood.api.assembler.old;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CozinhaOutputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoAssemblerOld {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaOutputDto toOutputDtoFromModel(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaOutputDto.class);
	}

	public List<CozinhaOutputDto> toCollectionOutputDtoFromModel(List<Cozinha> cozinhas) {
		return cozinhas.stream().map(cozinha -> toOutputDtoFromModel(cozinha)).collect(Collectors.toList());
	}
	
}
