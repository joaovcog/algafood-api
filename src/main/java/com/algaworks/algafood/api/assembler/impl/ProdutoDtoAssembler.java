package com.algaworks.algafood.api.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.dto.input.ProdutoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoOutputDto;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoDtoAssembler extends GenericInputOutputAssembler<Produto, ProdutoInputDto, ProdutoOutputDto> {

}
