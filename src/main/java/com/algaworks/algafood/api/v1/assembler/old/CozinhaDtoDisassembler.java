package com.algaworks.algafood.api.v1.assembler.old;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.dto.input.CozinhaInputDto;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CozinhaDtoDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObjectFromInputDto(CozinhaInputDto cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyFromInputDtoToDomainObject(CozinhaInputDto cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
	
}
