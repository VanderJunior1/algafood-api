package com.algaworks.algafood.config;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.algafood.exceptionhandler.ApiError;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer  {

	@Bean
	public Docket apiDocket() {
		TypeResolver typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.controller"))
					.build()
					.useDefaultResponseMessages(false)
					.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
					.globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
					.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
					.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
					.ignoredParameterTypes(ServletWebRequest.class,
							URL.class, URI.class, URLStreamHandler.class, Resource.class,
							File.class, InputStream.class)
					.additionalModels(typeResolver.resolve(ApiError.class))	
				.apiInfo(apiInfo())
				.securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()))
				.tags(
						new Tag("Cidades", "Gerencia as Cidades"),
						new Tag("Estados", "Gerencia os Estados"),
						new Tag("Cozinhas", "Gerencia os Cozinhas"),
						new Tag("Formas de Pagamentos", "Gerencia as Formas de Pagamentos"),
						new Tag("Grupos", "Gerencia os Grupos"),
						new Tag("Usuários", "Gerencia os Usuários"),
						new Tag("Restaurantes", "Gerencia os restaurantes"),
						new Tag("Produtos", "Gerencia os produtos de restaurantes"),
						new Tag("Pedidos", "Gerencia os Pedidosc")
					 	  );
	}
	
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisição inválida (erro do cliente)")
	                .responseModel(new ModelRef("Api Erros"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .responseModel(new ModelRef("Api Erros"))
	                .build()
	        );
	}

	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
	            new ResponseMessageBuilder()
	                .code(HttpStatus.BAD_REQUEST.value())
	                .message("Requisição inválida (erro do cliente)")
	                .responseModel(new ModelRef("Api Erros"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .message("Erro interno no servidor")
	                .responseModel(new ModelRef("Api Erros"))
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.NOT_ACCEPTABLE.value())
	                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
	                .build(),
	            new ResponseMessageBuilder()
	                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
	                .message("Requisição recusada porque o corpo está em um formato não suportado")
	                .responseModel(new ModelRef("Api Erros"))
	                .build()
	        );
	}

	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
						.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno do Servidor")
						.responseModel(new ModelRef("Api Erros"))
						.responseModel(new ModelRef("Api Erros"))
						.build(),
				new ResponseMessageBuilder()
						.code(HttpStatus.NOT_ACCEPTABLE.value())
						.message("Recurso não possui representação que pode ser aceita pelo consumidor")
						.build()
		);
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	    	.addResourceLocations("classpath:/META-INF/resources/");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("AlgaFood API")
				.description("API - plataforma para clientes e restaurantes")
				.version("1")
				.contact(new Contact("AlgaFood", "https://www.algafood.com.br", "contact@algafood.com.br"))
				.build();
	}
	
	private SecurityScheme securityScheme() {
		return new OAuthBuilder()
				.name("AlfaFood")
				.grantTypes(grantTypes())
				.scopes(scopes())
				.build();
	}

	private List<GrantType> grantTypes() {
		return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
	}
	
	private List<AuthorizationScope> scopes () {
		return Arrays.asList(
				new AuthorizationScope("READ", "Acesso de Leitura"),
				new AuthorizationScope("WRITE", "Acesso de Escrita"));
	}
	
	private SecurityContext securityContext() {
		var securityReference = SecurityReference.builder()
				.reference("AlfaFood")
				.scopes(scopes().toArray(new AuthorizationScope[0]))
				.build();
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(securityReference))
				.forPaths(PathSelectors.any())
				.build();
	}
}
