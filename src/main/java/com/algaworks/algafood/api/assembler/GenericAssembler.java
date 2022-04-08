package com.algaworks.algafood.api.assembler;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

@Getter
public abstract class GenericAssembler<T, I, O> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final Class<T> entityClass;
	private final Class<I> inputDtoClass;
	private final Class<O> outputDtoClass;
	
	@SuppressWarnings("unchecked")
	public GenericAssembler() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		
		this.entityClass = (Class<T>) type.getActualTypeArguments()[0];
		this.inputDtoClass = (Class<I>) type.getActualTypeArguments()[1];
		this.outputDtoClass = (Class<O>) type.getActualTypeArguments()[2];
	}
	
	public O toOutputDtoFromDomainEntity(T entity) {
		return modelMapper.map(entity, outputDtoClass);
	}

	public List<O> toCollectionOutputDtoFromDomainEntity(List<T> entityList) {
		return entityList.stream()
				.map(obj -> toOutputDtoFromDomainEntity(obj))
				.collect(Collectors.toList());
	}
	
	public T toDomainObjectFromInputDto(I inputDto) {
		return modelMapper.map(inputDto, entityClass);
	}
	
	public void copyFromInputDtoToDomainObject(I inputDto, T entity) {
		modelMapper.map(inputDto, entity);
	}
	
}
