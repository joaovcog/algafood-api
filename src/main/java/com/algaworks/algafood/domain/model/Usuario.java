package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;
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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "usuarios_grupos", 
		joinColumns = @JoinColumn(name = "cod_usuario"), 
		inverseJoinColumns = @JoinColumn(name = "cod_grupo"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public boolean vincularGrupo(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	public boolean desvincularGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}

	public boolean temGrupo(Long codGrupo) {
		return getGrupos().stream().anyMatch(g -> g.getCodigo().equals(codGrupo));
	}
	
	public boolean naoTemGrupo(Long codGrupo) {
		return !temGrupo(codGrupo);
	}
	
	public boolean isNovo() {
		return getCodigo() == null;
	}
	
}
