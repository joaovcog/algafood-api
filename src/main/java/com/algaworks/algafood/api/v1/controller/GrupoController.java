package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.impl.GrupoDtoAssembler;
import com.algaworks.algafood.api.v1.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.v1.dto.output.GrupoOutputDto;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/v1/grupos")
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoDtoAssembler grupoDtoAssembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoOutputDto> listar() {
		List<Grupo> grupos = grupoService.listar();

		return grupoDtoAssembler.toCollectionOutputDtoFromDomainEntity(grupos);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping(path = "/{codGrupo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoOutputDto buscar(@PathVariable Long codGrupo) {
		Grupo grupo = grupoService.buscarOuFalhar(codGrupo);

		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupo);
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoOutputDto adicionar(@RequestBody GrupoInputDto grupoInput) {
		Grupo grupo = grupoDtoAssembler.toDomainObjectFromInputDto(grupoInput);

		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupoService.salvar(grupo));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping(path = "/{codGrupo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoOutputDto atualizar(@PathVariable Long codGrupo, @RequestBody @Valid GrupoInputDto grupoInput) {
		Grupo grupoAtual = grupoService.buscarOuFalhar(codGrupo);

		grupoDtoAssembler.copyFromInputDtoToDomainObject(grupoInput, grupoAtual);

		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupoService.salvar(grupoAtual));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{codGrupo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codGrupo) {
		grupoService.excluir(codGrupo);
	}

}
