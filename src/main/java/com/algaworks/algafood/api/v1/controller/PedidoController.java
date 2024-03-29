package com.algaworks.algafood.api.v1.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.impl.PedidoDtoAssembler;
import com.algaworks.algafood.api.v1.assembler.impl.PedidoResumoDtoAssembler;
import com.algaworks.algafood.api.v1.dto.input.PedidoInputDto;
import com.algaworks.algafood.api.v1.dto.output.PedidoOutputDto;
import com.algaworks.algafood.api.v1.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoDtoAssembler pedidoDtoAssembler;

	@Autowired
	private PedidoResumoDtoAssembler pedidoResumoDtoAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;

	@CheckSecurity.Pedidos.PodePesquisar
	@GetMapping
	public Page<PedidoResumoOutputDto> pesquisar(PedidoFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		pageable = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoService.listar(filtro, pageable);

		List<PedidoResumoOutputDto> pedidosResumoOutputDto = pedidoResumoDtoAssembler
				.toCollectionOutputDtoFromDomainEntity(pedidosPage.getContent());
		
		Page<PedidoResumoOutputDto> pedidosResumoOutputDtoPage = new PageImpl<>(pedidosResumoOutputDto, pageable, pedidosPage.getTotalElements());
		
		return pedidosResumoOutputDtoPage;
	}

	@CheckSecurity.Pedidos.PodeBuscar
	@GetMapping("/{identificadorPedido}")
	public PedidoOutputDto buscar(@PathVariable String identificadorPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(identificadorPedido);

		return pedidoDtoAssembler.toOutputDtoFromDomainEntity(pedido);
	}

	@CheckSecurity.Pedidos.PodeCriar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoOutputDto adicionar(@RequestBody @Valid PedidoInputDto pedidoInput) {
		try {
			Pedido novoPedido = pedidoDtoAssembler.toDomainObjectFromInputDto(pedidoInput);

			novoPedido.setUsuarioCliente(new Usuario());
			novoPedido.getUsuarioCliente().setCodigo(algaSecurity.getCodUsuario());

			novoPedido = pedidoService.emitir(novoPedido);

			return pedidoDtoAssembler.toOutputDtoFromDomainEntity(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@PutMapping("/{identificadorPedido}/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable String identificadorPedido) {
		pedidoService.confirmar(identificadorPedido);
	}
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@PutMapping("/{identificadorPedido}/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable String identificadorPedido) {
		pedidoService.entregar(identificadorPedido);
	}
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@PutMapping("/{identificadorPedido}/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable String identificadorPedido) {
		pedidoService.cancelar(identificadorPedido);
	}

	private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"restaurante.nome", "restaurante.nome",
				"usuarioCliente.nome", "usuarioCliente.nome",
				"valorTotal", "valorTotal");
		
		return PageableTranslator.translate(pageable, mapeamento);
	}
	
}
