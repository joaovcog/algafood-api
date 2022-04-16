package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.ProdutoDtoAssembler;
import com.algaworks.algafood.api.dto.input.ProdutoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoOutputDto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes/{codRestaurante}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoDtoAssembler produtoDtoAssembler;
	
	@GetMapping
	public List<ProdutoOutputDto> listar(@PathVariable Long codRestaurante) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(codRestaurante);
		
		List<Produto> produtos = produtoService.listarPorRestaurante(restaurante);
		
		return produtoDtoAssembler.toCollectionOutputDtoFromDomainEntity(produtos);
	}
	
	@GetMapping("/{codProduto}")
	private ProdutoOutputDto buscar(@PathVariable Long codRestaurante, @PathVariable Long codProduto) {
		Produto produto = produtoService.buscarOuFalhar(codRestaurante, codProduto);
		
		return produtoDtoAssembler.toOutputDtoFromDomainEntity(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoOutputDto adicionar(@PathVariable Long codRestaurante, @RequestBody @Valid ProdutoInputDto produtoInput) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(codRestaurante);
		
		Produto produto = produtoDtoAssembler.toDomainObjectFromInputDto(produtoInput);
		produto.setRestaurante(restaurante);
		
		produto = produtoService.salvar(produto);
		
		return produtoDtoAssembler.toOutputDtoFromDomainEntity(produto);
	}
	
	@PutMapping("/{codProduto}")
	public ProdutoOutputDto atualizar(@PathVariable Long codRestaurante, @PathVariable Long codProduto, @RequestBody @Valid ProdutoInputDto produtoInput) {
		Produto produtoAtual = produtoService.buscarOuFalhar(codRestaurante, codProduto);
		
		produtoDtoAssembler.copyFromInputDtoToDomainObject(produtoInput, produtoAtual);
		
		produtoAtual = produtoService.salvar(produtoAtual);
		
		return produtoDtoAssembler.toOutputDtoFromDomainEntity(produtoAtual);
	}
}