package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("from Produto where restaurante.codigo = :restaurante and codigo = :produto")
	Optional<Produto> findById(@Param("restaurante") Long codRestaurante, @Param("produto") Long codProduto);
	
	List<Produto> findByRestaurante(Restaurante restaurante);
	
}
