package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.v1.dto.output.FormaPagamentoOutputDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security-auth")
public interface RestauranteFormaPagamentoControllerOpenApi {

	public List<FormaPagamentoOutputDto> listar(Long codRestaurante);
	
	public void vincularFormaPagamento(Long codRestaurante, Long codFormaPagamento);
	
	public void desvincularFormaPagamento(Long codRestaurante, Long codFormaPagamento);
	
}
