package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.GrupoDtoAssembler;
import com.algaworks.algafood.api.dto.input.GrupoInputDto;
import com.algaworks.algafood.api.dto.output.GrupoOutputDto;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoDtoAssembler grupoDtoAssembler;

	@GetMapping
	public List<GrupoOutputDto> listar() {
		List<Grupo> grupos = grupoService.listar();

		return grupoDtoAssembler.toCollectionOutputDtoFromDomainEntity(grupos);
	}

	@GetMapping("/{codigo}")
	public GrupoOutputDto buscar(@PathVariable Long codigo) {
		Grupo grupo = grupoService.buscarOuFalhar(codigo);

		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoOutputDto adicionar(@RequestBody GrupoInputDto grupoInput) {
		Grupo grupo = grupoDtoAssembler.toDomainObjectFromInputDto(grupoInput);

		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupoService.salvar(grupo));
	}

	@PutMapping("/{codigo}")
	public GrupoOutputDto atualizar(@PathVariable Long codigo, @RequestBody @Valid GrupoInputDto grupoInput) {
		Grupo grupoAtual = grupoService.buscarOuFalhar(codigo);

		grupoDtoAssembler.copyFromInputDtoToDomainObject(grupoInput, grupoAtual);

		return grupoDtoAssembler.toOutputDtoFromDomainEntity(grupoService.salvar(grupoAtual));
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		grupoService.excluir(codigo);
	}

}
