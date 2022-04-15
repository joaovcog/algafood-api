package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.dto.input.ProdutoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoOutputDto;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoDtoAssembler extends GenericAssembler<Produto, ProdutoInputDto, ProdutoOutputDto> {

}
