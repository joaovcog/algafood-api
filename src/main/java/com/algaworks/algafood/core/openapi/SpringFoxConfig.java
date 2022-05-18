package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.algaworks.algafood.api.exceptionhandler.ApiError;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
		TypeResolver typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30).select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
					// .paths(PathSelectors.ant("/restaurantes/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(ApiError.class))
				.apiInfo(apiInfo())
				.tags(tags()[0], tags());
	}
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("AlgaFood API").description("API desenvolvida no curso ESR da AlgaWorks")
				.version("1").contact(new Contact("AlgaWorks", "https://www.algaworks.com", "contato@algaworks.com"))
				.build();
	}

	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno do Servidor")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getApiErrorModelReference())
						.build(),
				new ResponseBuilder().code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("Recurso não possui representação que pode ser aceita pelo consumidor").build()
				);
	}
	
	private List<Response> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Requisição inválida (erro do cliente)")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getApiErrorModelReference())
						.build(),
				new ResponseBuilder().code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno no servidor")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getApiErrorModelReference())
						.build(),
				new ResponseBuilder().code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
						.build(),
				new ResponseBuilder().code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
						.description("Requisição recusada porque o corpo está em um formato não suportado")
						.representation(MediaType.APPLICATION_JSON)
						.apply(getApiErrorModelReference())
						.build()
				);
	}
	
	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder().code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Requisição inválida (erro do cliente)").build(),
				new ResponseBuilder().code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Erro interno no servidor").build()
				);
	}
	
	private Consumer<RepresentationBuilder> getApiErrorModelReference() {
	    return r -> r.model(m -> m.name("ApiError")
	            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
	                    q -> q.name("ApiError").namespace("com.algaworks.algafood.api.exceptionhandler")))));
	}

	private Tag[] tags() {
		return new Tag[] { 
				new Tag("Cidades", "Gerencia as cidades"), 
				new Tag("Cozinhas", "Gerencia as cozinhas"),
				new Tag("Grupos", "Gerencia os grupos")
			};
	}

}
