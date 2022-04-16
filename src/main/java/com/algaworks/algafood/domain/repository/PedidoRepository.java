package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("from Pedido p join fetch p.usuarioCliente "
			+ "join fetch p.restaurante "
			+ "join fetch p.restaurante.cozinha "
			+ "join fetch p.formaPagamento")
	List<Pedido> findAll();
	
}
