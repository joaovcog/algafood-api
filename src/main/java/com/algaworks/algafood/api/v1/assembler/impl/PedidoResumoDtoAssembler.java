package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericOutputAssembler;
import com.algaworks.algafood.api.v1.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoDtoAssembler extends GenericOutputAssembler<Pedido, PedidoResumoOutputDto> {

}
