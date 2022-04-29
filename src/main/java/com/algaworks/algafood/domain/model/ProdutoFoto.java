package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produtos_fotos")
public class ProdutoFoto {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produto_codigo")
	private Long codigo;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
	
	private String nomeArquivo;

	private String descricao;

	private String contentType;

	private Long tamanho;

	public Long getCodRestaurante() {
		if (getProduto() != null) {
			return getProduto().getRestaurante().getCodigo();
		}
		
		return null;
	}
	
}
