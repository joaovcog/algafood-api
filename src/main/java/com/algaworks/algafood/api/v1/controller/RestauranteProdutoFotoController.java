package com.algaworks.algafood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.assembler.impl.ProdutoFotoDtoAssembler;
import com.algaworks.algafood.api.v1.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.v1.dto.output.ProdutoFotoOutputDto;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.ProdutoFoto;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.domain.service.FotoStorageService.FotoRecuperada;
import com.algaworks.algafood.domain.service.ProdutoFotoService;
import com.algaworks.algafood.domain.service.ProdutoService;

@RestController
@RequestMapping("/v1/restaurantes/{codRestaurante}/produtos/{codProduto}/foto")
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

	@Autowired
	private ProdutoFotoService produtoFotoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private FotoStorageService fotoStorageService;

	@Autowired
	private ProdutoFotoDtoAssembler produtoFotoDtoAssembler;

	@CheckSecurity.Restaurantes.PodeConsultar
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoFotoOutputDto buscar(@PathVariable Long codRestaurante, @PathVariable Long codProduto) {
		ProdutoFoto produtoFoto = produtoFotoService.buscarOuFalhar(codRestaurante, codProduto);

		return produtoFotoDtoAssembler.toOutputDtoFromDomainEntity(produtoFoto);
	}

	@GetMapping
	public ResponseEntity<?> baixarFoto(@PathVariable Long codRestaurante,
			@PathVariable Long codProduto, @RequestHeader(name = "accept") String acceptHeader)
			throws HttpMediaTypeNotAcceptableException {
		try {
			ProdutoFoto produtoFoto = produtoFotoService.buscarOuFalhar(codRestaurante, codProduto);

			MediaType mediaTypeFoto = MediaType.parseMediaType(produtoFoto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(produtoFoto.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProdutoFotoOutputDto atualizarFoto(@PathVariable Long codRestaurante, @PathVariable Long codProduto,
			@Valid ProdutoFotoInputDto produtoFotoInput) throws IOException {
		Produto produto = produtoService.buscarOuFalhar(codRestaurante, codProduto);

		ProdutoFoto foto = criarFoto(produtoFotoInput, produto);
		ProdutoFoto fotoSalva = produtoFotoService.salvar(foto, produtoFotoInput.getArquivo().getInputStream());

		return produtoFotoDtoAssembler.toOutputDtoFromDomainEntity(fotoSalva);
	}

	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerFoto(@PathVariable Long codRestaurante,
			@PathVariable Long codProduto) {
		produtoFotoService.excluirFoto(codRestaurante, codProduto);
	}

	private ProdutoFoto criarFoto(ProdutoFotoInputDto produtoFotoInput, Produto produto) {
		ProdutoFoto foto = new ProdutoFoto();
		MultipartFile arquivo = produtoFotoInput.getArquivo();

		// foto.setCodigo(produto.getCodigo());
		foto.setProduto(produto);
		foto.setDescricao(produtoFotoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());

		return foto;
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas)
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypesAceitas.stream().anyMatch(mt -> mt.isCompatibleWith(mediaTypeFoto));

		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}

}
