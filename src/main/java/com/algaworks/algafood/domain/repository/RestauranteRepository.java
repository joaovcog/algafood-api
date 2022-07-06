package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	@Query("from Restaurante r join fetch r.cozinha where r.codigo in :codigos")
	List<Restaurante> findByCodigoIn(@Param("codigos") List<Long> codigos);
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//@Query("from Restaurante where nome like %:nome% and cozinha.codigo = :codCozinha")
	List<Restaurante> consultarPorNome(String nome, @Param("codCozinha") Long codCozinha);
	
	//List<Restaurante> findByNomeContainingAndCozinhaCodigo(String nome, Long codCozinha);
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	boolean existsResponsavel(Long codRestaurante, Long codUsuario);
	
}
