package com.algaworks.algafood.domain.exception;

public class ProdutoFotoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoFotoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoFotoNaoEncontradaException(Long codRestaurante, Long codProduto) {
		this(String.format("Não existe uma foto cadastrada para o produto de código %d no restaurante de código %d.", codProduto, codRestaurante));
	}
}
