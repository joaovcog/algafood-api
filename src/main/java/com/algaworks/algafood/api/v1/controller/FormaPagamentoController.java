package com.algaworks.algafood.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algaworks.algafood.api.v1.assembler.impl.FormaPagamentoDtoAssembler;
import com.algaworks.algafood.api.v1.dto.input.FormaPagamentoInputDto;
import com.algaworks.algafood.api.v1.dto.output.FormaPagamentoOutputDto;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;
	
	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping
	public ResponseEntity<List<FormaPagamentoOutputDto>> listar(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = gerarEtagListagem();
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamento> formasPagamentos = formaPagamentoService.listar();
		
		List<FormaPagamentoOutputDto> formasPagamentosOutputDto = formaPagamentoDtoAssembler.toCollectionOutputDtoFromDomainEntity(formasPagamentos);
		
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formasPagamentosOutputDto);
	}
	
	@CheckSecurity.FormasPagamento.PodeConsultar
	@GetMapping("/{codFormaPagamento}")
	public ResponseEntity<FormaPagamentoOutputDto> buscar(@PathVariable Long codFormaPagamento, ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = gerarEtagPorCodFormaPagamento(codFormaPagamento);
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(codFormaPagamento);
		
		FormaPagamentoOutputDto formaPagamentoOutputDto = formaPagamentoDtoAssembler.toOutputDtoFromDomainEntity(formaPagamento);
		
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formaPagamentoOutputDto);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoOutputDto adicionar(@RequestBody @Valid FormaPagamentoInputDto formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoDtoAssembler.toDomainObjectFromInputDto(formaPagamentoInput);
		formaPagamento = formaPagamentoService.salvar(formaPagamento);
		
		return formaPagamentoDtoAssembler.toOutputDtoFromDomainEntity(formaPagamento);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@PutMapping("/{codFormaPagamento}")
	public FormaPagamentoOutputDto atualizar(@PathVariable Long codFormaPagamento, @RequestBody @Valid FormaPagamentoInputDto formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(codFormaPagamento);
		
		formaPagamentoDtoAssembler.copyFromInputDtoToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		
		formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);
		
		return formaPagamentoDtoAssembler.toOutputDtoFromDomainEntity(formaPagamentoAtual);
	}
	
	@CheckSecurity.FormasPagamento.PodeEditar
	@DeleteMapping("/{codFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codFormaPagamento) {
		formaPagamentoService.excluir(codFormaPagamento);
	}
	
	private String gerarEtagListagem() {
		return gerarEtagPorCodFormaPagamento(null);
	}
	
	private String gerarEtagPorCodFormaPagamento(Long codFormaPagamento) {
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = null;
		
		if (codFormaPagamento != null) {
			dataUltimaAtualizacao = formaPagamentoService.getDataAtualizacao(codFormaPagamento);
		} else {	
			dataUltimaAtualizacao = formaPagamentoService.getDataUltimaAtualizacao();
		}
		
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		return eTag;
	}
	
}
