package com.algaworks.algafood.api.assembler.impl.representation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.generic.GenericOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoDtoRepresentationAssembler extends GenericOutputRepresentationAssembler<Pedido, PedidoResumoOutputDto, PedidoController> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoResumoDtoRepresentationAssembler() {
		super(PedidoController.class, PedidoResumoOutputDto.class);
	}

	@Override
	public PedidoResumoOutputDto toModel(Pedido entity) {
		PedidoResumoOutputDto pedidoResumoOutputDto = toOutputDtoFromDomainEntity(entity);
		
		pedidoResumoOutputDto.add(algaLinks.linkToPedidos(pedidoResumoOutputDto.getIdentificador()));
		
		pedidoResumoOutputDto.add(algaLinks.linkToPedidos());
		
		pedidoResumoOutputDto.getRestaurante().add(algaLinks.linkToRestaurante(entity.getRestaurante().getCodigo()));
        
		pedidoResumoOutputDto.getUsuarioCliente().add(algaLinks.linkToUsuarioCliente(entity.getUsuarioCliente().getCodigo()));
		
		return pedidoResumoOutputDto;
	}
	
}
