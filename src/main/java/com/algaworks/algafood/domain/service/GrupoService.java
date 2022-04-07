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
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso.";
	
	@Autowired
	private GrupoRepository grupoRepository;

	@Transactional
	public void salvar(Grupo grupo) {
		grupoRepository.save(grupo);
	}

	public Grupo buscarOuFalhar(Long codGrupo) {
		return grupoRepository.findById(codGrupo)
				.orElseThrow(() -> new GrupoNaoEncontradoException(codGrupo));
	}
	
	public List<Grupo> listar() {
		return grupoRepository.findAll();
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
