package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.ProdutoInputDto;
import com.algaworks.algafood.api.v1.dto.output.ProdutoOutputDto;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoDtoAssembler extends GenericInputOutputAssembler<Produto, ProdutoInputDto, ProdutoOutputDto> {

}
