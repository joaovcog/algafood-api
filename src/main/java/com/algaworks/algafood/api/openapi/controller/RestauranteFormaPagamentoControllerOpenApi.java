package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.dto.output.FormaPagamentoOutputDto;

public interface RestauranteFormaPagamentoControllerOpenApi {

	public List<FormaPagamentoOutputDto> listar(Long codRestaurante);
	
	public void vincularFormaPagamento(Long codRestaurante, Long codFormaPagamento);
	
	public void desvincularFormaPagamento(Long codRestaurante, Long codFormaPagamento);
	
}
