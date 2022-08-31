package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.dto.input.CidadeInputDto;
import com.algaworks.algafood.api.dto.output.CidadeOutputDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security-auth")
public interface CidadeControllerOpenApi {
	
	public PagedModel<CidadeOutputDto> listar(Pageable pageable);

	public CidadeOutputDto buscar(Long codCidade);

	public CidadeOutputDto adicionar(CidadeInputDto cidadeInput);

	public CidadeOutputDto atualizar(Long codCidade, CidadeInputDto cidadeInput);

	public void remover(Long codCidade);
	
}
