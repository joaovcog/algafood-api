package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoVinculadaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoVinculadaException(String mensagem) {
		super(mensagem);
	}
}
