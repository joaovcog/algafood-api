package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security-auth")
@Tag(name = "Cidades", description = "Gerencia as cidades")
public interface CidadeControllerOpenApi {
	
	@Operation(summary = "Lista as cidades")
	public PagedModel<CidadeOutputDto> listar(Pageable pageable);

	@Operation(summary = "Busca uma cidade por código")
	public CidadeOutputDto buscar(@Parameter(description = "Código de uma cidade", example = "1", required = true) Long codCidade);

	@Operation(summary = "Adiciona uma nova cidade", description = "Cadastro de uma cidade necessita de um estado e um nome válido")
	public CidadeOutputDto adicionar(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInputDto cidadeInput);

	@Operation(summary = "Atualiza uma cidade por código")
	public CidadeOutputDto atualizar(@Parameter(description = "Código de uma cidade", example = "1", required = true) Long codCidade, 
			@RequestBody(description = "Representação de uma cidade com dados atualizados", required = true) CidadeInputDto cidadeInput);

	@Operation(summary = "Remove uma cidade por código")
	public void remover(@Parameter(description = "Código de uma cidade", example = "1", required = true) Long codCidade);
	
}
