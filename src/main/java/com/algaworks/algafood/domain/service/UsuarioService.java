package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoService grupoService;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}

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
		return usuarioRepository.findById(codUsuario).orElseThrow(() -> new UsuarioNaoEncontradoException(codUsuario));
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@Transactional
	public void vincularGrupo(Long codUsuario, Long codGrupo) {
		Usuario usuario = buscarOuFalhar(codUsuario);

		if (usuario.naoTemGrupo(codGrupo)) {
			Grupo grupo = grupoService.buscarOuFalhar(codGrupo);

			usuario.vincularGrupo(grupo);
		}
	}

	@Transactional
	public void desvincularGrupo(Long codUsuario, Long codGrupo) {
		Usuario usuario = buscarOuFalhar(codUsuario);

		Grupo grupo = grupoService.buscarOuFalhar(codGrupo);

		usuario.desvincularGrupo(grupo);
	}

	private boolean isSenhaAtualInformadaIncorreta(String senhaAtual, String senhaAtualInformada) {
		return !senhaAtual.equals(senhaAtualInformada);
	}

}
