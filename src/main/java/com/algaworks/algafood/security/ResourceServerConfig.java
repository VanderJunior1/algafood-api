package com.algaworks.algafood.security;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors().and()
			.oauth2ResourceServer()
				.jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter());
	}

	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			var authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null) {
				authorities = Collections.emptyList();
			}
			
			return authorities.stream()
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		});
		
		return jwtAuthenticationConverter;
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		var secretKey = new SecretKeySpec("98a7f89jmas87fm9a87fn09af8afn089a7fa709fdm8asmdf".getBytes(), "HmacSHA256");
		return NimbusJwtDecoder.withSecretKey(secretKey).build();		
	}
}
