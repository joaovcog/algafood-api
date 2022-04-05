package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.CidadeOutputDto;
import com.algaworks.algafood.domain.model.Cidade;

@Component
public class CidadeDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeOutputDto toOutputDtoFromModel(Cidade estado) {
		return modelMapper.map(estado, CidadeOutputDto.class);
	}

	public List<CidadeOutputDto> toCollectionOutputDtoFromModel(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toOutputDtoFromModel(cidade)).collect(Collectors.toList());
	}
	
}