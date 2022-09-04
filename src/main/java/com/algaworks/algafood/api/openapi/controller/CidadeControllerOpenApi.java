package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security-auth")
@Tag(name = "Cidades", description = "Gerencia as cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	@PageableParameter
	public PagedModel<CidadeOutputDto> listar(@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Busca uma cidade por código", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Código da cidade inválido", content = @Content(schema = @Schema(ref = "ApiError"))),
			@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(ref = "ApiError"))) })
	public CidadeOutputDto buscar(
			@Parameter(description = "Código de uma cidade", example = "1", required = true) Long codCidade);

	@Operation(summary = "Adiciona uma nova cidade", description = "Cadastro de uma cidade necessita de um estado e um nome válido")
	public CidadeOutputDto adicionar(
			@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInputDto cidadeInput);

	@Operation(summary = "Atualiza uma cidade por código", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Código da cidade inválido", content = @Content(schema = @Schema(ref = "ApiError"))),
			@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(ref = "ApiError"))) })
	public CidadeOutputDto atualizar(
			@Parameter(description = "Código de uma cidade", example = "1", required = true) Long codCidade,
			@RequestBody(description = "Representação de uma cidade com dados atualizados", required = true) CidadeInputDto cidadeInput);

	@Operation(summary = "Remove uma cidade por código", responses = { @ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "Código da cidade inválido", content = @Content(schema = @Schema(ref = "ApiError"))),
			@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(ref = "ApiError"))) })
	public void remover(
			@Parameter(description = "Código de uma cidade", example = "1", required = true) Long codCidade);

}
