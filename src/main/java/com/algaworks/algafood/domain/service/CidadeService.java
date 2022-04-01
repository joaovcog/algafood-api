package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;

	@Transactional
	public Cidade salvar(Cidade cidade) {
		Long codEstado = cidade.getEstado().getCodigo();
		Estado estado = estadoService.buscarOuFalhar(codEstado);

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public Cidade buscarOuFalhar(Long codCidade) {
		return cidadeRepository.findById(codCidade).orElseThrow(() -> new CidadeNaoEncontradaException(codCidade));
	}

	@Transactional
	public void excluir(Long codigo) {
		try {
			cidadeRepository.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, codigo));
		}
	}

}
