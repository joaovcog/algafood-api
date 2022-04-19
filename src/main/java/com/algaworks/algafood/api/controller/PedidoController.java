package com.algaworks.algafood.api.controller;

import java.util.List;

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

import com.algaworks.algafood.api.assembler.impl.PedidoDtoAssembler;
import com.algaworks.algafood.api.assembler.impl.PedidoResumoDtoAssembler;
import com.algaworks.algafood.api.dto.input.PedidoInputDto;
import com.algaworks.algafood.api.dto.output.PedidoOutputDto;
import com.algaworks.algafood.api.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoDtoAssembler pedidoDtoAssembler;

	@Autowired
	private PedidoResumoDtoAssembler pedidoResumoDtoAssembler;

	@GetMapping
	public Page<PedidoResumoOutputDto> pesquisar(PedidoFilter filtro, @PageableDefault(size = 5) Pageable pageable) {
		Page<Pedido> pedidosPage = pedidoService.listar(filtro, pageable);

		List<PedidoResumoOutputDto> pedidosResumoOutputDto = pedidoResumoDtoAssembler
				.toCollectionOutputDtoFromDomainEntity(pedidosPage.getContent());
		
		Page<PedidoResumoOutputDto> pedidosResumoOutputDtoPage = new PageImpl<>(pedidosResumoOutputDto, pageable, pedidosPage.getTotalElements());
		
		return pedidosResumoOutputDtoPage;
	}

	@GetMapping("/{identificadorPedido}")
	public PedidoOutputDto buscar(@PathVariable String identificadorPedido) {
		Pedido pedido = pedidoService.buscarOuFalhar(identificadorPedido);

		return pedidoDtoAssembler.toOutputDtoFromDomainEntity(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoOutputDto adicionar(@RequestBody @Valid PedidoInputDto pedidoInput) {
		try {
			Pedido novoPedido = pedidoDtoAssembler.toDomainObjectFromInputDto(pedidoInput);

			// TODO vincular usuário autenticado
			novoPedido.setUsuarioCliente(new Usuario());
			novoPedido.getUsuarioCliente().setCodigo(1L);

			novoPedido = pedidoService.emitir(novoPedido);

			return pedidoDtoAssembler.toOutputDtoFromDomainEntity(novoPedido);
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

}
