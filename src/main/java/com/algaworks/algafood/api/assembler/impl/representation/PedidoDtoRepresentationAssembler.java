package com.algaworks.algafood.api.assembler.impl.representation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.GenericInputOutputRepresentationAssembler;
import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.dto.input.PedidoInputDto;
import com.algaworks.algafood.api.dto.output.PedidoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoDtoRepresentationAssembler extends GenericInputOutputRepresentationAssembler<Pedido, PedidoInputDto, PedidoOutputDto, PedidoController> {
	
	private final Class<PedidoController> controllerClass;
	
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
		
		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM)
				);
		
		String pedidosUrl = linkTo(controllerClass).toUri().toString();
		
		pedidoOutputDto.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables), "pedidos"));
		
//		pedidoOutputDto.add(linkTo(controllerClass)
//				.withRel("pedidos"));
		
		pedidoOutputDto.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(entity.getRestaurante().getCodigo())).withSelfRel());
        
		pedidoOutputDto.getUsuarioCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(entity.getUsuarioCliente().getCodigo())).withSelfRel());
        
		pedidoOutputDto.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(entity.getFormaPagamento().getCodigo(), null)).withSelfRel());
        
		pedidoOutputDto.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(entity.getEnderecoEntrega().getCidade().getCodigo())).withSelfRel());
        
		pedidoOutputDto.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoOutputDto.getRestaurante().getCodigo(), item.getCodigoProduto()))
                    .withRel("produto"));
        });
		
		return pedidoOutputDto;
	}
	
}
