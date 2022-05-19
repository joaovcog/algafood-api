package com.algaworks.algafood.api.openapi.controller;

import java.io.IOException;

import com.algaworks.algafood.api.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoFotoOutputDto;
import com.algaworks.algafood.api.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Código do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = ApiError.class))), 
		@ApiResponse(responseCode = "404", description = "Foto do produto não encontrada", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public ProdutoFotoOutputDto buscar(@ApiParam(value = "Código do restaurante", example = "1", required = true) Long codRestaurante, 
			@ApiParam(value = "Código do produto", example = "1", required = true) Long codProduto);

	@ApiOperation(value = "Atualiza a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Foto do produto atualizada"), 
		@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public ProdutoFotoOutputDto atualizarFoto(@ApiParam(value = "Código do restaurante", example = "1", required = true) Long codRestaurante, 
			@ApiParam(value = "Código do produto", example = "1", required = true) Long codProduto,
			ProdutoFotoInputDto produtoFotoInput) throws IOException;

	@ApiOperation(value = "Exclui a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
		@ApiResponse(responseCode = "400", description = "Código do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = ApiError.class))),
		@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public void removerFoto(@ApiParam(value = "Código do restaurante", example = "1", required = true) Long codRestaurante,
			@ApiParam(value = "Código do produto", example = "1", required = true) Long codProduto);
	
}
