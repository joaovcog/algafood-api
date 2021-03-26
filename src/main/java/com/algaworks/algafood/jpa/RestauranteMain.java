package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class RestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = restauranteRepository.listar();
		
		for (Restaurante r : restaurantes) {
			System.out.println(r.getNome() + " - " + r.getTaxaFrete() + " - " + r.getCozinha().getNome());
		}
		
		System.out.println("\n------------------------\n");
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
		
		List<Cidade> cidades = cidadeRepository.listar();
		
		for (Cidade c : cidades) {
			System.out.println(c.getCodigo() + " - " + c.getNome() + " - " + c.getEstado().getNome());
		}
		
		System.out.println("\n------------------------\n");
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		List<FormaPagamento> formasPagamento = formaPagamentoRepository.listar();
		
		for (FormaPagamento fp : formasPagamento) {
			System.out.println(fp.getCodigo() + " - " + fp.getDescricao());
		}
		
		System.out.println("\n------------------------\n");
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		List<Permissao> permissoes = permissaoRepository.listar();
		
		for (Permissao p : permissoes) {
			System.out.println(p.getCodigo() + " - " + p.getNome() + " - " + p.getDescricao());
		}
	}
	
}