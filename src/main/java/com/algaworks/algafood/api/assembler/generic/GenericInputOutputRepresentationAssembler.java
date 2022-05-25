package com.algaworks.algafood.api.assembler.generic;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.lang.reflect.ParameterizedType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import lombok.Getter;

@Getter
public abstract class GenericInputOutputRepresentationAssembler<T, I, O extends RepresentationModel<O>, C> extends RepresentationModelAssemblerSupport<T, O> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final Class<T> entityClass;
	private final Class<I> inputDtoClass;
	private final Class<O> outputDtoClass;
	private final Class<C> controllerClass;
	
	@SuppressWarnings("unchecked")
	public GenericInputOutputRepresentationAssembler(Class<C> controllerClass, Class<O> outputDtoClass) {
		super(controllerClass, outputDtoClass);
		
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		
		this.entityClass = (Class<T>) type.getActualTypeArguments()[0];
		this.inputDtoClass = (Class<I>) type.getActualTypeArguments()[1];
		this.outputDtoClass = outputDtoClass;
		this.controllerClass = controllerClass;
	}
	
	protected O toOutputDtoFromDomainEntity(T entity) {
		return modelMapper.map(entity, outputDtoClass);
	}
	
	@Override
	public CollectionModel<O> toCollectionModel(Iterable<? extends T> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(controllerClass).withSelfRel());
	}

	public T toDomainObjectFromInputDto(I inputDto) {
		return modelMapper.map(inputDto, entityClass);
	}
	
	public void copyFromInputDtoToDomainObject(I inputDto, T entity) {
		modelMapper.map(inputDto, entity);
	}
	
}
