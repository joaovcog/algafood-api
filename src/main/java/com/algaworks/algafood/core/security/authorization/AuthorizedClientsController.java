package com.algaworks.algafood.core.security.authorization;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthorizedClientsController {

	private final OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService;
	private final RegisteredClientRepository registeredClientRepository;
	private final OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService;
	private final OAuth2AuthorizationService auth2AuthorizationService;

	@GetMapping("/oauth2/authorized-clients")
	public String clientsList(Principal principal, Model model) {
		List<RegisteredClient> clients = oAuth2AuthorizationQueryService.listClientsWithConsent(principal.getName());

		model.addAttribute("clients", clients);

		return "pages/authorized-clients";
	}

	@PostMapping("/oauth2/authorized-clients/revoke")
	public String revoke(Principal principal, Model model,
			@RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId) {
		RegisteredClient client = this.registeredClientRepository.findByClientId(clientId);

		if (client == null) {
			throw new AccessDeniedException(String.format("Client %s não encontrado", clientId));
		}

		OAuth2AuthorizationConsent consent = this.oAuth2AuthorizationConsentService.findById(client.getId(),
				principal.getName());

		List<OAuth2Authorization> authorizations = this.oAuth2AuthorizationQueryService
				.listAuthorizations(principal.getName(), client.getId());

		if (consent != null) {
			this.oAuth2AuthorizationConsentService.remove(consent);
		}

		for (OAuth2Authorization authorization : authorizations) {
            this.auth2AuthorizationService.remove(authorization);
        }


		return "redirect:/oauth2/authorized-clients";
	}

}