package com.CordyTech.Puerto.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Puerto API",
        version = "v1",
        description = "API REST para gestión de puertos"
    )
)
public class OpenApiConfig {
}
