package com.algaworks.algafood.api.assembler.generic;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

@Getter
public abstract class GenericOutputAssembler<T, O> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final Class<T> entityClass;
	private final Class<O> outputDtoClass;
	
	@SuppressWarnings("unchecked")
	public GenericOutputAssembler() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		
		this.entityClass = (Class<T>) type.getActualTypeArguments()[0];
		this.outputDtoClass = (Class<O>) type.getActualTypeArguments()[1];
	}
	
	public O toOutputDtoFromDomainEntity(T entity) {
		return modelMapper.map(entity, outputDtoClass);
	}

	public List<O> toCollectionOutputDtoFromDomainEntity(Collection<T> entityCollection) {
		return entityCollection.stream()
				.map(obj -> toOutputDtoFromDomainEntity(obj))
				.collect(Collectors.toList());
	}
	
}
