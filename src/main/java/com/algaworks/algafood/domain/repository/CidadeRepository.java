package com.algaworks.algafood.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	@Query(value = "from Cidade c join fetch c.estado", 
			countQuery = "select count(c.codigo) from Cidade c")
	Page<Cidade> findAllPaginado(Pageable pageable);
	
}
