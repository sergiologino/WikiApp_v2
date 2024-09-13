package ru.altacod.wikiapp.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springWikiAppOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("WikiApp API")
                        .description("Документация API для WikiApp")
                        .version("v0.0.1"));
    }
}

