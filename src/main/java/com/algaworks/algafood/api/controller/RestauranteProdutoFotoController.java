package com.algaworks.algafood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.assembler.impl.ProdutoFotoDtoAssembler;
import com.algaworks.algafood.api.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoFotoOutputDto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.ProdutoFoto;
import com.algaworks.algafood.domain.service.ProdutoFotoService;
import com.algaworks.algafood.domain.service.ProdutoService;

@RestController
@RequestMapping("/restaurantes/{codRestaurante}/produtos/{codProduto}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private ProdutoFotoService produtoFotoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoFotoDtoAssembler produtoFotoDtoAssembler;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProdutoFotoOutputDto atualizarFoto(@PathVariable Long codRestaurante, @PathVariable Long codProduto,
			@Valid ProdutoFotoInputDto produtoFotoInput) throws IOException {
		Produto produto = produtoService.buscarOuFalhar(codRestaurante, codProduto);
		
		ProdutoFoto foto = criarFoto(produtoFotoInput, produto);
		ProdutoFoto fotoSalva = produtoFotoService.salvar(foto, produtoFotoInput.getArquivo().getInputStream());
		
		return produtoFotoDtoAssembler.toOutputDtoFromDomainEntity(fotoSalva);
	}

	private ProdutoFoto criarFoto(ProdutoFotoInputDto produtoFotoInput, Produto produto) {
		ProdutoFoto foto = new ProdutoFoto();
		MultipartFile arquivo = produtoFotoInput.getArquivo();
		
		//foto.setCodigo(produto.getCodigo());
		foto.setProduto(produto);
		foto.setDescricao(produtoFotoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		return foto;
	}

}
