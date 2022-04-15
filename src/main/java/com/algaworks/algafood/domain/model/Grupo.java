package com.algaworks.algafood.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "grupos")
public class Grupo {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(nullable = false)
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupos_permissoes", 
		joinColumns = @JoinColumn(name = "cod_grupo"), 
		inverseJoinColumns = @JoinColumn(name = "cod_permissao"))
	private Set<Permissao> permissoes = new HashSet<>();
	
	public boolean vincularPermissao(Permissao permissao) {
		return getPermissoes().add(permissao);
	}
	
	public boolean desvincularPermissao(Permissao permissao) {
		return getPermissoes().remove(permissao);
	}
	
	public boolean temPermissao(Long codPermissao) {
		return getPermissoes().stream().anyMatch(p -> p.getCodigo().equals(codPermissao));
	}
	
	public boolean naoTemPermissao(Long codPermissao) {
		return !temPermissao(codPermissao);
	}
	
}
