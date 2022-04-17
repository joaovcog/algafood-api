package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		
		return pedidoRepository.save(pedido);
	}
	
	public Pedido buscarOuFalhar(Long codPedido) {
		return pedidoRepository.findById(codPedido).orElseThrow(() -> 
			new PedidoNaoEncontradoException(codPedido));
	}
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	private void validarPedido(Pedido pedido) {
		Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getCodigo());
		Usuario usuario = usuarioService.buscarOuFalhar(pedido.getUsuarioCliente().getCodigo());
		Restaurante restaurante = restauranteService.buscarOuFalhar(pedido.getRestaurante().getCodigo());
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getCodigo());
		
		pedido.getEnderecoEntrega().setCidade(cidade);
		pedido.setUsuarioCliente(usuario);
		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		
		if (restaurante.naoTemFormaPagamento(formaPagamento.getCodigo())) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.", formaPagamento.getDescricao()));
		}
	}
	
	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = produtoService.buscarOuFalhar(pedido.getRestaurante().getCodigo(), item.getProduto().getCodigo());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
	
}
