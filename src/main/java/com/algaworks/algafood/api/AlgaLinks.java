package com.algaworks.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.controller.FormaPagamentoController;
import com.algaworks.algafood.api.controller.PedidoController;
import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.controller.RestauranteUsuarioResponsavelController;
import com.algaworks.algafood.api.controller.UsuarioController;
import com.algaworks.algafood.api.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public Link linkToPedidos() {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("codCliente", VariableType.REQUEST_PARAM),
				new TemplateVariable("codRestaurante", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
		
		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(pedidosUrl, PAGE_VARIABLES.concat(filtroVariables)), "pedidos");
	}
	
	public Link linkToPedidos(String identificador) {
		return linkTo(methodOn(PedidoController.class)
				.buscar(identificador))
				.withSelfRel();
	}
	
	public Link linkToConfirmacaoPedido(String identificador, String rel) {
		return linkTo(methodOn(PedidoController.class)
				.confirmar(identificador))
				.withRel(rel);
	}
	
	public Link linkToEntregaPedido(String identificador, String rel) {
		return linkTo(methodOn(PedidoController.class)
				.entregar(identificador))
				.withRel(rel);
	}
	
	public Link linkToCancelamentoPedido(String identificador, String rel) {
		return linkTo(methodOn(PedidoController.class)
				.cancelar(identificador))
				.withRel(rel);
	}
	
	public Link linkToRestaurante(Long codRestaurante) {
		return linkTo(methodOn(RestauranteController.class)
                .buscar(codRestaurante)).withSelfRel();
	}
	
	public Link linkToResponsaveisRestaurante(Long codRestaurante) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
				.listar(codRestaurante))
				.withSelfRel();
	}
	
	public Link linkToUsuarioCliente(Long codUsuarioCliente) {
		return linkTo(methodOn(UsuarioController.class)
                .buscar(codUsuarioCliente)).withSelfRel();
	}
	
	public Link linkToUsuariosClientes(String rel) {
		return linkTo(UsuarioController.class)
				.withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long codUsuarioCliente, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class)
				.listar(codUsuarioCliente))
				.withRel(rel);
	}
	
	public Link linkToFormaPagamento(Long codFormaPagamento) {
		return linkTo(methodOn(FormaPagamentoController.class)
                .buscar(codFormaPagamento, null)).withSelfRel();
	}
	
	public Link linkToCidade(Long codCidade) {
		return linkTo(methodOn(CidadeController.class)
                .buscar(codCidade)).withSelfRel();
	}
	
	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class)
				.withRel(rel);
	}
	
	public Link linkToProduto(Long codRestaurante, Long codProduto) {
		return linkTo(methodOn(RestauranteProdutoController.class)
                .buscar(codRestaurante, codProduto))
                .withRel("produto");
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class)
				.withRel(rel);
	}
	
	public Link linkToEstado(Long codEstado) {
		return linkTo(methodOn(EstadoController.class)
				.buscar(codEstado)).withSelfRel();
	}
	
	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class)
				.withRel(rel);
	}
	
}
