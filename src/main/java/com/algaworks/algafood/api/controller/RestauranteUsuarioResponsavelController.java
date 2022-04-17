package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.impl.UsuarioDtoAssembler;
import com.algaworks.algafood.api.dto.output.UsuarioOutputDto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{codRestaurante}/usuarios")
public class RestauranteUsuarioResponsavelController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private UsuarioDtoAssembler usuarioDtoAssembler;
	
	@GetMapping
	public List<UsuarioOutputDto> listar(@PathVariable Long codRestaurante) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(codRestaurante);
		
		return usuarioDtoAssembler.toCollectionOutputDtoFromDomainEntity(restaurante.getUsuariosResponsaveis());
	}
	
	@PutMapping("/{codUsuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vincularUsuariosResponsaveis(@PathVariable Long codRestaurante, @PathVariable Long codUsuario) {
		restauranteService.vincularUsuarioResponsavel(codRestaurante, codUsuario);
	}
	
	@DeleteMapping("/{codUsuario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desvincularUsuariosResponsaveis(@PathVariable Long codRestaurante, @PathVariable Long codUsuario) {
		restauranteService.desvincularUsuarioResponsavel(codRestaurante, codUsuario);
	}
	
}
