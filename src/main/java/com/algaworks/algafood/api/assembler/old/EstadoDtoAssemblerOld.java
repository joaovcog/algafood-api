package com.algaworks.algafood.api.assembler.old;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.output.EstadoOutputDto;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoDtoAssemblerOld {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoOutputDto toOutputDtoFromModel(Estado estado) {
		return modelMapper.map(estado, EstadoOutputDto.class);
	}

	public List<EstadoOutputDto> toCollectionOutputDtoFromModel(List<Estado> estados) {
		return estados.stream().map(estado -> toOutputDtoFromModel(estado)).collect(Collectors.toList());
	}
	
}
