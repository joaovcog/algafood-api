package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.dto.output.FormaPagamentoOutputDto;
import com.algaworks.algafood.api.exceptionhandler.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

	@ApiOperation("Lista as formas de pagamento associadas a restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public List<FormaPagamentoOutputDto> listar(@ApiParam(value = "Código do restaurante", example = "1", required = true) Long codRestaurante);
	
	@ApiOperation("Vinculação de restaurante com forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Vinculação realizada com sucesso"), 
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public void vincularFormaPagamento(@ApiParam(value = "Código do restaurante", example = "1", required = true) Long codRestaurante, 
			@ApiParam(value = "Código da forma de pagamento", example = "1", required = true) Long codFormaPagamento);
	
	@ApiOperation("Desvinculação de restaurante com forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desvinculação realizada com sucesso"), 
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = ApiError.class)))
	})
	public void desvincularFormaPagamento(@ApiParam(value = "Código do restaurante", example = "1", required = true) Long codRestaurante, 
			@ApiParam(value = "Código da forma de pagamento", example = "1", required = true) Long codFormaPagamento);
	
}
