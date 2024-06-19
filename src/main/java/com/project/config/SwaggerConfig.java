package com.project.config;


import java.util.Collections;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;




@Configuration
@OpenAPIDefinition(
		info=@Info(
				title="MakerSharks Assignment Apis",
				description="Curd APIs",
				version="1.0",
				contact=@Contact(
						name="Shivam Maurya",
						email="shivammaurya@gmail.com",
						url="https://springshop.wiki.github.org/docs"
						),
				license=@License
				(
						name="Apache 2.0",
						url="http://springdoc.org"
						)
				
				),
		externalDocs = @ExternalDocumentation(
				description="SpringShop Wiki Documentation",
				url="https://springshop.wiki.github.org/docs"
				
				)
		
		
		)

@SecurityScheme(
		name="scheme1",
		type=SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
		)
public class SwaggerConfig {
}
