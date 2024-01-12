package com.slimdevs.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
    
    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Account's Microservice REST API.")
                .description("SlimDev Account's Microservice REST API Documentation")
                .version("v1")
                .contact(new Contact()
                        .name("Santi")
                        .email("maildeprueba*@gmail.com")
                        .url("https://www.linkedin.com/in/santiagomoline/"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("urlprueba.com"))
                )
            .externalDocs(new ExternalDocumentation()
                .description("SlimDev Account's Microservice REST API Documentation")
                .url("http://localhost:8080/swagger-ui/index.html"));
    }
}
