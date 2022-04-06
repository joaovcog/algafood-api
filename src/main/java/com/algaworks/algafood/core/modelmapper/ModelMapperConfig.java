package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoOutputDto;
import com.algaworks.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		addMappings(modelMapper);

		return modelMapper;
	}
	
	private void addMappings(ModelMapper modelMapper) {
		var enderecoToOutputDtoTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoOutputDto.class);

		enderecoToOutputDtoTypeMap.<String>addMapping(enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoOutputDest, value) -> enderecoOutputDest.getCidade().setEstado(value));
	}

}
