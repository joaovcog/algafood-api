package com.algaworks.algafood.api.controller;

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

import com.algaworks.algafood.api.assembler.impl.FormaPagamentoDtoAssembler;
import com.algaworks.algafood.api.dto.input.FormaPagamentoInputDto;
import com.algaworks.algafood.api.dto.output.FormaPagamentoOutputDto;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoDtoAssembler formaPagamentoDtoAssembler;
	
	@GetMapping
	public ResponseEntity<List<FormaPagamentoOutputDto>> listar() {
		List<FormaPagamento> formasPagamentos = formaPagamentoService.listar();
		
		List<FormaPagamentoOutputDto> formasPagamentosOutputDto = formaPagamentoDtoAssembler.toCollectionOutputDtoFromDomainEntity(formasPagamentos);
		
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formasPagamentosOutputDto);
	}
	
	@GetMapping("/{codFormaPagamento}")
	public ResponseEntity<FormaPagamentoOutputDto> buscar(@PathVariable Long codFormaPagamento) {
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(codFormaPagamento);
		
		FormaPagamentoOutputDto formaPagamentoOutputDto = formaPagamentoDtoAssembler.toOutputDtoFromDomainEntity(formaPagamento);
		
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formaPagamentoOutputDto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoOutputDto adicionar(@RequestBody @Valid FormaPagamentoInputDto formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoDtoAssembler.toDomainObjectFromInputDto(formaPagamentoInput);
		formaPagamento = formaPagamentoService.salvar(formaPagamento);
		
		return formaPagamentoDtoAssembler.toOutputDtoFromDomainEntity(formaPagamento);
	}
	
	@PutMapping("/{codFormaPagamento}")
	public FormaPagamentoOutputDto atualizar(@PathVariable Long codFormaPagamento, @RequestBody @Valid FormaPagamentoInputDto formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(codFormaPagamento);
		
		formaPagamentoDtoAssembler.copyFromInputDtoToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		
		formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);
		
		return formaPagamentoDtoAssembler.toOutputDtoFromDomainEntity(formaPagamentoAtual);
	}
	
	@DeleteMapping("/{codFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codFormaPagamento) {
		formaPagamentoService.excluir(codFormaPagamento);
	}
}
