package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.FormaPagamentoInputDto;
import com.algaworks.algafood.api.dto.output.FormaPagamentoOutputDto;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDtoAssembler extends GenericAssembler<FormaPagamento, FormaPagamentoInputDto, FormaPagamentoOutputDto> {

}
