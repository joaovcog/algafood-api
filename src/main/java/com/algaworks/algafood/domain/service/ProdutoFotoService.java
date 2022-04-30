package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.ProdutoFotoNaoEncontradaException;
import com.algaworks.algafood.domain.model.ProdutoFoto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class ProdutoFotoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public ProdutoFoto salvar(ProdutoFoto produtoFoto, InputStream dadosArquivo) {
		Long codRestaurante = produtoFoto.getCodRestaurante();
		Long codProduto = produtoFoto.getProduto().getCodigo();
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(produtoFoto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<ProdutoFoto> fotoExistente = produtoRepository.findFotoById(codRestaurante, codProduto);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		produtoFoto.setNomeArquivo(nomeNovoArquivo);
		produtoFoto = produtoRepository.save(produtoFoto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(produtoFoto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();
		
		if (nomeArquivoExistente != null) {
			fotoStorageService.remover(nomeArquivoExistente);
		}
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return produtoFoto;
	}
	
	@Transactional
	public void excluirFoto(Long codRestaurante, Long codProduto) {
		ProdutoFoto produtoFoto = buscarOuFalhar(codRestaurante, codProduto);
		
		produtoRepository.delete(produtoFoto);
		produtoRepository.flush();
		
		fotoStorageService.remover(produtoFoto.getNomeArquivo());
	}

	public ProdutoFoto buscarOuFalhar(Long codRestaurante, Long codProduto) {
		return produtoRepository.findFotoById(codRestaurante, codProduto)
				.orElseThrow(() -> new ProdutoFotoNaoEncontradaException(codRestaurante, codProduto));
	}
	
}
