package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
	MENSAGEM_ILEGIVEL("/mensagem-ilegivel", "Mensagem ilegível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"), 
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"), 
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	private ApiErrorType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
