package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void atualizarSenha(Long codUsuario, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(codUsuario);
		
		if (isSenhaAtualInformadaIncorreta(usuario.getSenha(), senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(novaSenha);
	}

	public Usuario buscarOuFalhar(Long codUsuario) {
		return usuarioRepository.findById(codUsuario)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(codUsuario));
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	private boolean isSenhaAtualInformadaIncorreta(String senhaAtual, String senhaAtualInformada) {
		return !senhaAtual.equals(senhaAtualInformada);
	}

}
