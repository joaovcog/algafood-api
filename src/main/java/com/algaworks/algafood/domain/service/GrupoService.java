package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso.";

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private PermissaoService permissaoService;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	public Grupo buscarOuFalhar(Long codGrupo) {
		return grupoRepository.findById(codGrupo).orElseThrow(() -> new GrupoNaoEncontradoException(codGrupo));
	}

	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}

	@Transactional
	public void vincularPermissao(Long codGrupo, Long codPermissao) {
		Grupo grupo = buscarOuFalhar(codGrupo);

		if (grupo.naoTemPermissao(codPermissao)) {
			Permissao permissao = permissaoService.buscarOuFalhar(codPermissao);

			grupo.vincularPermissao(permissao);
		}
	}

	@Transactional
	public void desvincularPermissao(Long codGrupo, Long codPermissao) {
		Grupo grupo = buscarOuFalhar(codGrupo);

		Permissao permissao = permissaoService.buscarOuFalhar(codPermissao);

		grupo.desvincularPermissao(permissao);
	}

	@Transactional
	public void excluir(Long codGrupo) {
		try {
			grupoRepository.deleteById(codGrupo);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(codGrupo);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, codGrupo));
		}
	}

}
