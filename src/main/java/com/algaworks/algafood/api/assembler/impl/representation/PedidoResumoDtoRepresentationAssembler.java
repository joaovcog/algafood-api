package com.algaworks.algafood.api.assembler.impl.representation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.GenericOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoDtoRepresentationAssembler extends GenericOutputRepresentationAssembler<Pedido, PedidoResumoOutputDto, PedidoController> {
	
	private final Class<PedidoController> controllerClass;
	
	public PedidoResumoDtoRepresentationAssembler() {
		super(PedidoController.class, PedidoResumoOutputDto.class);
		
		this.controllerClass = PedidoController.class;
	}

	@Override
	public PedidoResumoOutputDto toModel(Pedido entity) {
		PedidoResumoOutputDto pedidoResumoOutputDto = toOutputDtoFromDomainEntity(entity);
		
		pedidoResumoOutputDto.add(linkTo(methodOn(controllerClass)
				.buscar(pedidoResumoOutputDto.getIdentificador()))
				.withSelfRel());
		
		pedidoResumoOutputDto.add(linkTo(controllerClass)
				.withRel("pedidos"));
		
		pedidoResumoOutputDto.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(entity.getRestaurante().getCodigo())).withSelfRel());
        
		pedidoResumoOutputDto.getUsuarioCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(entity.getUsuarioCliente().getCodigo())).withSelfRel());
		
		return pedidoResumoOutputDto;
	}
	
}
