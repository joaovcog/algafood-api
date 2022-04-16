package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.output.PedidoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoDtoAssembler extends GenericAssembler<Pedido, PedidoOutputDto, PedidoOutputDto> {

}
