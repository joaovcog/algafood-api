package com.algaworks.algafood.api.v1.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoOutputDto {
	
	private Long codigo;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Boolean ativo;
	
}
