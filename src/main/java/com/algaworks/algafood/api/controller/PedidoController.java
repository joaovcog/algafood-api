package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoDtoAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoDtoAssembler;
import com.algaworks.algafood.api.dto.output.PedidoOutputDto;
import com.algaworks.algafood.api.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoDtoAssembler pedidoDtoAssembler;
	
	@Autowired
	private PedidoResumoDtoAssembler pedidoResumoDtoAssembler;
	
	@GetMapping
	public List<PedidoResumoOutputDto> listar() {
		List<Pedido> pedidos = pedidoService.listar();
		
		return pedidoResumoDtoAssembler.toCollectionOutputDtoFromDomainEntity(pedidos);
	}
	
	@GetMapping("/{codPedido}")
	public PedidoOutputDto buscar(@PathVariable Long codPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(codPedido);
		
		return pedidoDtoAssembler.toOutputDtoFromDomainEntity(pedido);
	}
	
}
