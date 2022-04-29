package com.algaworks.algafood.domain.service;

import java.util.Optional;

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
		Long codRestaurante = produtoFoto.getCodRestaurante();
		Long codProduto = produtoFoto.getProduto().getCodigo();
		
		Optional<ProdutoFoto> fotoExistente = produtoRepository.findFotoById(codRestaurante, codProduto);
		
		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}
		
		return produtoRepository.save(produtoFoto);
	}
	
}
