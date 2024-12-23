package com.codewithdurgesh.blog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.lang.Arrays;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI myCustomConfig() {
		return new OpenAPI().info(
			new Info().title("Blogging Application")
			.description("By Prayas Gupta")
		)
				.servers(List.of(new Server().url("http://localhost:9091").description("local"),
						new Server().url("http://localhost:9092").description("live"))).components(new Components().addSecuritySchemes(
								"bearerAuth",new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWt")
								.in(SecurityScheme.In.HEADER)
								.name("Authorization")
								
								
								
								
								));
				
				
			
				
				
				
	}
	
	
	
	
}
