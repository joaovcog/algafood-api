package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;
import com.algaworks.algafood.api.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {
	
	@ApiOperation("Lista as cidades")
	public PagedModel<CidadeOutputDto> listar(Pageable pageable);

	@ApiOperation("Busca uma cidade por código")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Código da cidade inválido", content = @Content(schema = @Schema(implementation = ApiError.class))), 
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public CidadeOutputDto buscar(@ApiParam(value = "Código de uma cidade") Long codCidade);

	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cidade cadastrada") 
	})
	public CidadeOutputDto adicionar(CidadeInputDto cidadeInput);

	@ApiOperation("Atualiza uma cidade por código")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cidade atualizada"), 
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public CidadeOutputDto atualizar(Long codCidade, CidadeInputDto cidadeInput);

	@ApiOperation("Exclui uma cidade por código")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cidade excluída"), 
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public void remover(@ApiParam(value = "Código de uma cidade") Long codCidade);
	
}
