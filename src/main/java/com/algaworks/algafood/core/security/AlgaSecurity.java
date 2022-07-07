package com.algaworks.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class AlgaSecurity {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getCodUsuario() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		
		return jwt.getClaim("cod_usuario");
	}
	
	public boolean isResponsavelRestaurante(Long codRestaurante) {
		if (codRestaurante == null) {
			return false;
		}
		
		return restauranteRepository.existsResponsavel(codRestaurante, getCodUsuario());
	}
	
	public boolean isResponsavelRestaurantePedido(String identificadorPedido) {
		return pedidoRepository.isPedidoGerenciadoPor(identificadorPedido, getCodUsuario());
	}
	
	public boolean isUsuarioAutenticadoIgual(Long codUsuario) {
		return getCodUsuario() != null && codUsuario != null && getCodUsuario().equals(codUsuario);
	}
	
}
