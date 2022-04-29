package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.ProdutoFoto;

public interface ProdutoRepositoryQueries {

	ProdutoFoto save(ProdutoFoto foto);
	
	void delete(ProdutoFoto foto);
	
}
