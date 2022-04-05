package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericAssembler<T, I, O> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public O toOutputDtoFromDomainEntity(T entity, Class<O> outputDtoClass) {
		return modelMapper.map(entity, outputDtoClass);
	}

	public List<O> toCollectionOutputDtoFromDomainEntity(List<T> entityList, Class<O> outputDtoClass) {
		return entityList.stream()
				.map(obj -> toOutputDtoFromDomainEntity(obj, outputDtoClass))
				.collect(Collectors.toList());
	}
	
	public T toDomainObjectFromInputDto(I inputDto, Class<T> entityClass) {
		return modelMapper.map(inputDto, entityClass);
	}
	
	public void copyFromInputDtoToDomainObject(I inputDto, T entity) {
		modelMapper.map(inputDto, entity);
	}
	
}
