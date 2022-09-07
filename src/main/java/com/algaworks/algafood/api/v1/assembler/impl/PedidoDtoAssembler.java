package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.PedidoInputDto;
import com.algaworks.algafood.api.v1.dto.output.PedidoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoDtoAssembler extends GenericInputOutputAssembler<Pedido, PedidoInputDto, PedidoOutputDto> {

}
