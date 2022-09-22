package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.domain.service.EnvioEmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockEnvioEmailService implements EnvioEmailService {
	
	@Autowired
	private ProcessadorTemplateEmail processadorTemplateEmail;
	
	@Override
	public void enviar(Mensagem mensagem) {
		String corpo = processadorTemplateEmail.processarTemplate(mensagem);
		
		log.info("[MOCK E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
	}

}
