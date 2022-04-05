package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.EstadoInputDto;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDtoDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObjectFromInputDto(EstadoInputDto estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void copyFromInputDtoToDomainObject(EstadoInputDto estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
}
