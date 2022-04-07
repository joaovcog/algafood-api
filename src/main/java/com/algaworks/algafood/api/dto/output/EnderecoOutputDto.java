package com.algaworks.algafood.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoOutputDto {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResumoOutputDto cidade;

}
