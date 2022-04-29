package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.ProdutoFoto;
import com.algaworks.algafood.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
	
	@Query("from Produto p where p.restaurante.codigo = :restaurante and p.codigo = :produto")
	Optional<Produto> findById(@Param("restaurante") Long codRestaurante, @Param("produto") Long codProduto);
	
	List<Produto> findTodosByRestaurante(Restaurante restaurante);
	
	@Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	List<Produto> findAtivosByRestaurante(Restaurante restaurante);
	
	@Query("select f from ProdutoFoto f join f.produto p where p.restaurante.codigo = :codRestaurante and f.produto.codigo = :codProduto")
	Optional<ProdutoFoto> findFotoById(Long codRestaurante, Long codProduto);
	
}
