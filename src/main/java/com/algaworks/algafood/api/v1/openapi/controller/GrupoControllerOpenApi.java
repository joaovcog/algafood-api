package com.algaworks.algafood.api.v1.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.v1.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.v1.dto.output.GrupoOutputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security-auth")
@Tag(name = "Grupos", description = "Gerencia os grupos de usuários")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	public List<GrupoOutputDto> listar();

	@Operation(summary = "Busca um grupo por código", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Código do grupo inválido", content = @Content(schema = @Schema(ref = "ApiError"))),
			@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "ApiError"))) })
	public GrupoOutputDto buscar(
			@Parameter(description = "Código de um grupo", example = "1", required = true) Long codGrupo);

	@Operation(summary = "Adiciona um novo grupo", description = "Cadastro de um grupo necessita de um nome válido")
	public GrupoOutputDto adicionar(
			@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInputDto grupoInput);

	@Operation(summary = "Atualiza um grupo por código", responses = { @ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Código do grupo inválido", content = @Content(schema = @Schema(ref = "ApiError"))),
			@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(ref = "ApiError"))) })
	public GrupoOutputDto atualizar(
			@Parameter(description = "Código de um grupo", example = "1", required = true) Long codGrupo,
			@RequestBody(description = "Representação de um grupo com dados atualizados", required = true) GrupoInputDto grupoInput);

	@Operation(summary = "Remove um grupo por código", responses = {
			@ApiResponse(responseCode = "204"), 
			@ApiResponse(responseCode = "400", 
				description = "Código do grupo inválido", 
				content = @Content(schema = @Schema(ref = "ApiError"))), 
			@ApiResponse(responseCode = "404", 
				description = "Grupo não encontrado", 
				content = @Content(schema = @Schema(ref = "ApiError")))
		})
	public void excluir(@Parameter(description = "Código de um grupo", example = "1", required = true) Long codGrupo);

}
