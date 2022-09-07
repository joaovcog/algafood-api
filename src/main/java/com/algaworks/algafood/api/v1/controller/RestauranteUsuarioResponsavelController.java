package com.algaworks.algafood.api.v1.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.impl.UsuarioDtoRepresentationAssembler;
import com.algaworks.algafood.api.v1.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/v1/restaurantes/{codRestaurante}/usuarios")
public class RestauranteUsuarioResponsavelController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioDtoRepresentationAssembler usuarioDtoRepresentationAssembler;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping
	public CollectionModel<UsuarioOutputDto> listar(@PathVariable Long codRestaurante) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(codRestaurante);
		
		return usuarioDtoRepresentationAssembler.toCollectionModel(restaurante.getUsuariosResponsaveis())
				.removeLinks()
				.add(linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
						.listar(codRestaurante))
						.withSelfRel());
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@PutMapping("/{codUsuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vincularUsuariosResponsaveis(@PathVariable Long codRestaurante, @PathVariable Long codUsuario) {
		restauranteService.vincularUsuarioResponsavel(codRestaurante, codUsuario);
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@DeleteMapping("/{codUsuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desvincularUsuariosResponsaveis(@PathVariable Long codRestaurante, @PathVariable Long codUsuario) {
		restauranteService.desvincularUsuarioResponsavel(codRestaurante, codUsuario);
	}
	
}
