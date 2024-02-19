package com.example.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Oasys Track").version("4.1").description("Oasys Dashboad API"))
				.addSecurityItem(new SecurityRequirement().addList("Oasys Track"))
				.components(new Components().addSecuritySchemes("Oasys Track", new SecurityScheme()
						.name("Oasys Track").type(SecurityScheme.Type.HTTP).scheme("bearer")));
	}
}
