package com.algaworks.algafood.api.assembler.impl.representation;

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
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoDtoRepresentationAssembler() {
		super(PedidoController.class, PedidoOutputDto.class);
	}

	@Override
	public PedidoOutputDto toModel(Pedido entity) {
		PedidoOutputDto pedidoOutputDto = toOutputDtoFromDomainEntity(entity);
		
		if (entity.podeSerConfirmado()) {
			pedidoOutputDto.add(algaLinks.linkToConfirmacaoPedido(entity.getIdentificador(), "confirmar"));
		}
		
		if (entity.podeSerCancelado()) {
			pedidoOutputDto.add(algaLinks.linkToCancelamentoPedido(entity.getIdentificador(), "cancelar"));
		}
		
		if (entity.podeSerEntregue()) {
			pedidoOutputDto.add(algaLinks.linkToEntregaPedido(entity.getIdentificador(), "entregar"));
		}
		
		pedidoOutputDto.add(algaLinks.linkToPedidos(entity.getIdentificador()));
		
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
