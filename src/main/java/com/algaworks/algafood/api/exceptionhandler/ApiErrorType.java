package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");
	
	private String title;
	private String uri;
	
	private ApiErrorType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
