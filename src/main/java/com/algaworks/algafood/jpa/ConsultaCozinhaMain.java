package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		Cozinha c1 = new Cozinha();
		c1.setCodigo(1L);
		c1.setNome("Teste");
		Cozinha c2 = new Cozinha();
		c2.setCodigo(1L);
		c2.setNome("aaaaaaaaaaaaaaa");
		
		System.out.println(c1.equals(c2));
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		List<Cozinha> cozinhas = cozinhaRepository.listar();
		
		for (Cozinha c : cozinhas) {
			System.out.println(c.getNome());
		}
		
		System.out.println("Por codigo");
		Cozinha cozinha = cozinhaRepository.buscar(1L);
		
		System.out.println(cozinha.getNome());
	}

}
