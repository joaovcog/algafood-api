package com.algaworks.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algaworks.algafood.api.v1.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.v1.dto.output.ProdutoFotoOutputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security-auth")
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Busca a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoFotoOutputDto.class)), 
					@Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")), 
					@Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
			})
	})
	public ProdutoFotoOutputDto buscar(Long codRestaurante, Long codProduto);

	@Operation(summary = "Atualiza a foto do produto de um restaurante")
	public ProdutoFotoOutputDto atualizarFoto(@Parameter(description = "Código do restaurante", example = "1", required = true) Long codRestaurante, 
			@Parameter(description = "Código do produto", example = "2", required = true) Long codProduto,
			@RequestBody(required = true) ProdutoFotoInputDto produtoFotoInput) throws IOException;

	@Operation(hidden = true)
	ResponseEntity<?> baixarFoto(Long codRestaurante, Long codProduto, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	public void removerFoto(Long codRestaurante, Long codProduto);

}
