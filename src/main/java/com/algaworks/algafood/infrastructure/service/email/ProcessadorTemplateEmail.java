package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;
import com.algaworks.algafood.infrastructure.service.email.exception.EmailException;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class ProcessadorTemplateEmail {

	@Autowired
	private Configuration freemarkerConfig;
	
	protected String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getModelosTemplate());
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail.", e);
		}
	}
	
}
