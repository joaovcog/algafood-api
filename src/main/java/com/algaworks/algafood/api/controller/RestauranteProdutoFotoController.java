package com.algaworks.algafood.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.dto.input.FotoProdutoInputDto;

@RestController
@RequestMapping("/restaurantes/{codRestaurante}/produtos/{codProduto}/foto")
public class RestauranteProdutoFotoController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long codRestaurante, @PathVariable Long codProduto,
			@Valid FotoProdutoInputDto fotoProdutoInput) {
		String nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
		
		Path pathArquivoFoto = Path.of("C:\\Users\\joaov\\Pictures\\catalogo", nomeArquivo);
		
		System.out.println(fotoProdutoInput.getDescricao());
		System.out.println(pathArquivoFoto);
		System.out.println(fotoProdutoInput.getArquivo().getContentType());
		
		try {
			fotoProdutoInput.getArquivo().transferTo(pathArquivoFoto);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
