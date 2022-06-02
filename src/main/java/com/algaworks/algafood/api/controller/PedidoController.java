package com.algaworks.algafood.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.impl.representation.PedidoDtoRepresentationAssembler;
import com.algaworks.algafood.api.assembler.impl.representation.PedidoResumoDtoRepresentationAssembler;
import com.algaworks.algafood.api.dto.input.PedidoInputDto;
import com.algaworks.algafood.api.dto.output.PedidoOutputDto;
import com.algaworks.algafood.api.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoDtoRepresentationAssembler pedidoDtoRepresentationAssembler;

	@Autowired
	private PedidoResumoDtoRepresentationAssembler pedidoResumoDtoRepresentationAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<PedidoResumoOutputDto> pesquisar(PedidoFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoService.listar(filtro, pageableTraduzido);
		
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);

		PagedModel<PedidoResumoOutputDto> pedidosPagedModel = pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoDtoRepresentationAssembler);
		
		return pedidosPagedModel;
	}

	@GetMapping("/{identificadorPedido}")
	public PedidoOutputDto buscar(@PathVariable String identificadorPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(identificadorPedido);

		return pedidoDtoRepresentationAssembler.toModel(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoOutputDto adicionar(@RequestBody @Valid PedidoInputDto pedidoInput) {
		try {
			Pedido novoPedido = pedidoDtoRepresentationAssembler.toDomainObjectFromInputDto(pedidoInput);

			// TODO vincular usuário autenticado
			novoPedido.setUsuarioCliente(new Usuario());
			novoPedido.getUsuarioCliente().setCodigo(1L);

			novoPedido = pedidoService.emitir(novoPedido);

			return pedidoDtoRepresentationAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{identificadorPedido}/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable String identificadorPedido) {
		pedidoService.confirmar(identificadorPedido);
	}

	@PutMapping("/{identificadorPedido}/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable String identificadorPedido) {
		pedidoService.entregar(identificadorPedido);
	}

	@PutMapping("/{identificadorPedido}/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable String identificadorPedido) {
		pedidoService.cancelar(identificadorPedido);
	}

	private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"nomeRest", "restaurante.nome",
				"usuarioCliente.nome", "usuarioCliente.nome",
				"valorTotal", "valorTotal");
		
		return PageableTranslator.translate(pageable, mapeamento);
	}
	
}
