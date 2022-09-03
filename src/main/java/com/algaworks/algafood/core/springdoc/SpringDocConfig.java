package com.algaworks.algafood.core.springdoc;

import java.util.HashMap;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.exceptionhandler.ApiError;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
@SecurityScheme(name = "security-auth", 
	type = SecuritySchemeType.OAUTH2, 
	flows = @OAuthFlows(authorizationCode = @OAuthFlow(
			authorizationUrl = "${springdoc.oauth-flow.authorization-url}", 
			tokenUrl = "${springdoc.oauth-flow.token-url}", 
			scopes = {
					@OAuthScope(name = "READ", description = "read scope"), 
					@OAuthScope(name = "WRITE", description = "write scope")
				}
			)
		)
	)
public class SpringDocConfig {
	
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info()
						.title("AlgaFood API")
						.version("v1")
						.description("REST API do AlgaFood")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.com")
						)
				).components(new Components().schemas(gerarSchemas()));
	}
	
	@Bean
	public OpenApiCustomiser openApiCustomiser() {
		return openApi -> { 
			openApi.getPaths()
				.values()
				.forEach(pathItem -> pathItem.readOperationsMap()
						.forEach((httpMethod, operation) -> {
							ApiResponses responses = operation.getResponses();
							
							switch (httpMethod) {
							case GET:
								responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado."));
								responses.addApiResponse("406", new ApiResponse().description("Recurso não possui representação que poderia ser aceita pelo consumidor."));
								responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor."));
								break;
							case POST:
								responses.addApiResponse("400", new ApiResponse().description("Requisição inválida."));
								responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor."));
								break;
							case PUT:
								responses.addApiResponse("400", new ApiResponse().description("Requisição inválida."));
								responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado."));
								responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor."));
								break;
							case DELETE:
								responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado."));
								responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor."));
								break;
							default:
								responses.addApiResponse("500", new ApiResponse().description("Erro interno no servidor."));
								break;
						}
				})
			);
				
		};
	}
	
	private Map<String, Schema> gerarSchemas() {
		final Map<String, Schema> schemaMap = new HashMap<>();
		
		Map<String, Schema> problemSchema = ModelConverters.getInstance().read(ApiError.class);
		Map<String, Schema> errorObjectSchema = ModelConverters.getInstance().read(ApiError.Object.class);
		
		schemaMap.putAll(problemSchema);
		schemaMap.putAll(errorObjectSchema);
		
		return schemaMap;
	}
	
}
