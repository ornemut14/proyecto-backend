package com.ucc.orders.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ordersOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Órdenes")
                        .description("Microservicio para gestión de órdenes")
                        .version("1.0.0"));}
}