package com.algaworks.algafood.api.assembler.old;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class CidadeDtoDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObjectFromInputDto(CidadeInputDto cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyFromInputDtoToDomainObject(CidadeInputDto cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
	
}
