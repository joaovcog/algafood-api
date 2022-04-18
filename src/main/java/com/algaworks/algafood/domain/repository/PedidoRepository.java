package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
	
	Optional<Pedido> findByIdentificador(String identificador);
	
	@Query("from Pedido p "
			+ "join fetch p.usuarioCliente "
			+ "join fetch p.restaurante r "
			+ "join fetch r.cozinha "
			+ "join fetch p.formaPagamento "
			+ "join fetch p.enderecoEntrega e "
			+ "join fetch e.cidade c "
			+ "join fetch c.estado")
	List<Pedido> findAll();
	
}
