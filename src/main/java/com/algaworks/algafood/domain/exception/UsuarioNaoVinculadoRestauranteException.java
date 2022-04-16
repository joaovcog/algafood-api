package com.algaworks.algafood.domain.exception;

public class UsuarioNaoVinculadoRestauranteException extends EntidadeNaoVinculadaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoVinculadoRestauranteException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoVinculadoRestauranteException(Long codRestaurante, Long codUsuario) {
		this(String.format("Não existe um cadastro de usuário com código %d vinculado ao restaurante de código %d.", codUsuario, codRestaurante));
	}
}
