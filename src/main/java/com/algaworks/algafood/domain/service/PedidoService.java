package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscarOuFalhar(Long codPedido) {
		return pedidoRepository.findById(codPedido).orElseThrow(() -> 
			new PedidoNaoEncontradoException(codPedido));
	}
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
}
