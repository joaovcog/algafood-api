package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> listar();
	Cozinha buscar(Long codigo);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}