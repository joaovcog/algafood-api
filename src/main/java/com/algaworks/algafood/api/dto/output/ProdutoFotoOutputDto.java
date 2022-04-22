package com.algaworks.algafood.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFotoOutputDto {

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}
