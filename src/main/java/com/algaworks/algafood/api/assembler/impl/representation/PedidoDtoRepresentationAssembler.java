package com.algaworks.algafood.api.assembler.impl.representation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.dto.input.PedidoInputDto;
import com.algaworks.algafood.api.dto.output.PedidoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Pedido, PedidoInputDto, PedidoOutputDto, PedidoController> {
	
	private final Class<PedidoController> controllerClass;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoDtoRepresentationAssembler() {
		super(PedidoController.class, PedidoOutputDto.class);
		
		this.controllerClass = PedidoController.class;
	}

	@Override
	public PedidoOutputDto toModel(Pedido entity) {
		PedidoOutputDto pedidoOutputDto = toOutputDtoFromDomainEntity(entity);
		
		pedidoOutputDto.add(linkTo(methodOn(controllerClass)
				.buscar(pedidoOutputDto.getIdentificador()))
				.withSelfRel());
		
		pedidoOutputDto.add(algaLinks.linkToPedidos());
		
		pedidoOutputDto.getRestaurante().add(algaLinks.linkToRestaurante(entity.getRestaurante().getCodigo()));
        
		pedidoOutputDto.getUsuarioCliente().add(algaLinks.linkToUsuarioCliente(entity.getUsuarioCliente().getCodigo()));
        
		pedidoOutputDto.getFormaPagamento().add(algaLinks.linkToFormaPagamento(entity.getFormaPagamento().getCodigo()));
        
		pedidoOutputDto.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(entity.getEnderecoEntrega().getCidade().getCodigo()));
        
		pedidoOutputDto.getItens().forEach(item -> {
            item.add(algaLinks.linkToProduto(pedidoOutputDto.getRestaurante().getCodigo(), item.getCodigoProduto()));
        });
		
		return pedidoOutputDto;
	}
	
}
