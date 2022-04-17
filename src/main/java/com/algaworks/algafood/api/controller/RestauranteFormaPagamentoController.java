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

import com.algaworks.algafood.api.assembler.impl.FormaPagamentoDtoAssembler;
import com.algaworks.algafood.api.dto.output.FormaPagamentoOutputDto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{codRestaurante}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;
	
	@GetMapping
	public List<FormaPagamentoOutputDto> listar(@PathVariable Long codRestaurante) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(codRestaurante);
		
		return formaPagamentoDtoAssembler.toCollectionOutputDtoFromDomainEntity(restaurante.getFormasPagamento());
	}
	
	@PutMapping("/{codFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vincularFormaPagamento(@PathVariable Long codRestaurante, @PathVariable Long codFormaPagamento) {
		restauranteService.vincularFormaPagamento(codRestaurante, codFormaPagamento);
	}
	
	@DeleteMapping("/{codFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desvincularFormaPagamento(@PathVariable Long codRestaurante, @PathVariable Long codFormaPagamento) {
		restauranteService.desvincularFormaPagamento(codRestaurante, codFormaPagamento);
	}

	
}
