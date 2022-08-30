package com.algaworks.algafood.api.openapi.controller;

import java.io.IOException;

import com.algaworks.algafood.api.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoFotoOutputDto;

public interface RestauranteProdutoFotoControllerOpenApi {
	
	public ProdutoFotoOutputDto buscar(Long codRestaurante, Long codProduto);

	public ProdutoFotoOutputDto atualizarFoto(Long codRestaurante, Long codProduto,
			ProdutoFotoInputDto produtoFotoInput) throws IOException;

	public void removerFoto(Long codRestaurante, Long codProduto);
	
}
