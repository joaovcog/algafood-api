package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ApiErrorType {
	ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	MENSAGEM_ILEGIVEL("/mensagem-ilegivel", "Mensagem ilegível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	RECURSO_NAO_VINCULADO("/recurso-nao-vinculado", "Recurso não vinculado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"), 
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"), 
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");
	
	private String title;
	private String uri;
	
	private ApiErrorType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
