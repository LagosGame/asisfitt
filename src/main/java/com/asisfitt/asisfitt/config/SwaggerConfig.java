package com.asisfitt.asisfitt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AsisFitt API")
                        .version("1.0")
                        .description("API REST para gestión de un box de crossfit en Ciudad de Asís. " +
                                "Permite gestionar usuarios, WODs, clases y reservas con capacidad máxima.")
                        .contact(new Contact()
                                .name("Germán Cabrera")
                                .email("germancabrerita@gmail.com")
                        )
                );
    }
}
