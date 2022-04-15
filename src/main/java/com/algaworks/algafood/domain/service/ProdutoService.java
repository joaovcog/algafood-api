package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto buscarOuFalhar(Long codRestaurante, Long codProduto) {
		return produtoRepository.findById(codRestaurante, codProduto)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(codRestaurante, codProduto));
	}
	
	public List<Produto> listarPorRestaurante(Restaurante restaurante) {
		return produtoRepository.findByRestaurante(restaurante);
	}

	/*@Transactional
	public void excluir(Long codFormaPagamento) {
		try {
			formaPagamentoRepository.deleteById(codFormaPagamento);
			formaPagamentoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(codFormaPagamento);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, codFormaPagamento));
		}
	}*/
	
}
