package com.algaworks.algafood.api.v1.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.v1.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.v1.dto.output.ProdutoFotoOutputDto;
import com.algaworks.algafood.domain.model.ProdutoFoto;

@Component
public class ProdutoFotoDtoAssembler extends GenericInputOutputAssembler<ProdutoFoto, ProdutoFotoInputDto, ProdutoFotoOutputDto> {

}
