package com.develop.web.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
     @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("NPS 데모 프로젝트")
                .version("ALPHA 20230519")
                .description("Network production system API Document");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}