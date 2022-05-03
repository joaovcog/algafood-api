package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;

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
	
	@Autowired
	private EnvioEmailService envioEmailService;

	@Transactional
	public Pedido emitir(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);

		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();

		return pedidoRepository.save(pedido);
	}

	@Transactional
	public void confirmar(String identificadorPedido) {
		Pedido pedido = buscarOuFalhar(identificadorPedido);
		
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("O pedido de código <strong>" + pedido.getCodigo() + "</strong> foi confirmado!")
				.destinatario(pedido.getUsuarioCliente().getEmail())
				.build();
		
		envioEmailService.enviar(mensagem);
	}
	
	@Transactional
	public void entregar(String identificadorPedido) {
		Pedido pedido = buscarOuFalhar(identificadorPedido);
		
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(String identificadorPedido) {
		Pedido pedido = buscarOuFalhar(identificadorPedido);
		
		pedido.cancelar();
	}

	public Pedido buscarOuFalhar(String identificadorPedido) {
		return pedidoRepository.findByIdentificador(identificadorPedido).orElseThrow(() -> new PedidoNaoEncontradoException(identificadorPedido));
	}

	public Page<Pedido> listar(PedidoFilter filtro, Pageable pageable) {
		return pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
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
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
					formaPagamento.getDescricao()));
		}
	}

	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = produtoService.buscarOuFalhar(pedido.getRestaurante().getCodigo(),
					item.getProduto().getCodigo());

			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
	
//	private Pedido retornarPedidoComStatusValidado(Long codPedido, StatusPedido statusInicial, StatusPedido statusFinal) {
//		Pedido pedido = buscarOuFalhar(codPedido);
//
//		if (!pedido.getStatus().equals(statusInicial)) {
//			String mensagem = String.format("Status do pedido %d não pode ser alterado de %s para %s.",
//					codPedido, pedido.getStatus().getDescricao(), statusFinal.getDescricao());
//			
//			if (pedido.getStatus().equals(statusFinal)) {
//				mensagem = String.format("Pedido %d já foi %s.", codPedido, statusFinal.getDescricao());
//			}
//			
//			throw new NegocioException(mensagem);
//		}
//		
//		pedido.setStatus(statusFinal);
//		
//		return pedido;
//	}

}
