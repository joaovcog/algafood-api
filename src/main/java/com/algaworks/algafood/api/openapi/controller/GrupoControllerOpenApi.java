package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.dto.output.GrupoOutputDto;

public interface GrupoControllerOpenApi {
	
	public List<GrupoOutputDto> listar();
	
	public GrupoOutputDto buscar(Long codGrupo);
	
	public GrupoOutputDto adicionar(GrupoInputDto grupoInput);
	
	public GrupoOutputDto atualizar(Long codGrupo, GrupoInputDto grupoInput);
	
	public void excluir(Long codGrupo);
	
}
