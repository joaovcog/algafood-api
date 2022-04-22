package com.algaworks.algafood.api.assembler.impl;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.assembler.generic.GenericInputOutputAssembler;
import com.algaworks.algafood.api.dto.input.ProdutoFotoInputDto;
import com.algaworks.algafood.api.dto.output.ProdutoFotoOutputDto;
import com.algaworks.algafood.domain.model.ProdutoFoto;

@Component
public class ProdutoFotoDtoAssembler extends GenericInputOutputAssembler<ProdutoFoto, ProdutoFotoInputDto, ProdutoFotoOutputDto> {

}
