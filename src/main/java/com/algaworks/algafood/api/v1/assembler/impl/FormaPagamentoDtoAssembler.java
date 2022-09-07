package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.FormaPagamentoInputDto;
import com.algaworks.algafood.api.v1.dto.output.FormaPagamentoOutputDto;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDtoAssembler extends GenericInputOutputAssembler<FormaPagamento, FormaPagamentoInputDto, FormaPagamentoOutputDto> {

}
