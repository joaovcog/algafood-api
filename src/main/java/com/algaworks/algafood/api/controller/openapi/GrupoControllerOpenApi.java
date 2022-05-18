package com.algaworks.algafood.api.controller.openapi;

import java.util.List;

import com.algaworks.algafood.api.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.dto.output.GrupoOutputDto;
import com.algaworks.algafood.api.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {
	
	@ApiOperation("Lista os grupos")
	public List<GrupoOutputDto> listar();

	@ApiOperation("Busca um grupo por código")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Código do grupo inválido", content = @Content(schema = @Schema(implementation = ApiError.class))), 
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public GrupoOutputDto buscar(@ApiParam(value = "Código de um grupo", example = "1") Long codGrupo);

	@ApiOperation("Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Grupo cadastrado") 
	})
	public GrupoOutputDto adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInputDto grupoInput);

	@ApiOperation("Atualiza um grupo por código")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Grupo atualizado"), 
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
	public GrupoOutputDto atualizar(@ApiParam(value = "Código de um grupo", example = "1") Long codGrupo, 
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados") GrupoInputDto grupoInput);

	@ApiOperation("Exclui um grupo por código")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "Grupo excluído"), 
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
	public void excluir(@ApiParam(value = "Código de um grupo", example = "1") Long codGrupo);
	
}
