package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.ProdutoFoto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class ProdutoFotoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public ProdutoFoto salvar(ProdutoFoto produtoFoto) {
		return produtoRepository.save(produtoFoto);
	}
	
}
