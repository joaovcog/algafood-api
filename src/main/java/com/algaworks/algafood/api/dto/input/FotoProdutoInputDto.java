package com.algaworks.algafood.api.dto.input;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInputDto {
	
	private MultipartFile arquivo;
	private String descricao;
	
}
