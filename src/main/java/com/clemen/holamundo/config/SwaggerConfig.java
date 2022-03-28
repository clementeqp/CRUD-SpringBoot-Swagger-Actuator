package com.clemen.holamundo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * https://springfox.github.io/springfox/docs/current/
 * https://swagger.io/tools/swagger-ui/
 * Configuración Swagger para la generación de documentación de la API REST
 * http://localhost:8080/swagger-ui/
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clemen.holamundo.controller"))
                //.apis(RequestHandlerSelectors.any()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Generador de Saludos en diferentes idiomas",
                "Idiomas Api Doc",
                "1.0",
                "https://www.google.com",
                new Contact("Clemente Quintana Pozo", "https://clementeqp.github.io/Breaking-Bad/", "clemen@mail.com"),
                "MIT",
                "https://www.google.com",
                Collections.emptyList());
    }

}