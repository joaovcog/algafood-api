package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.output.PedidoResumoOutputDto;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoDtoAssembler extends GenericAssembler<Pedido, PedidoResumoOutputDto, PedidoResumoOutputDto> {

}
