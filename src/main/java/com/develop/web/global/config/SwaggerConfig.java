package com.develop.web.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi commonGroup() {
        return GroupedOpenApi.builder()
            .group("일반 API")
            .pathsToMatch(
                "/common/**",
                "/user/**",
                "/folder/**",
                "/team/**",
                "/ingest/**",
                "/clip/**",
                "/notice/**",
                "/check/**",
                "/auth/**")
            .build();
    }

    @Bean
    public GroupedOpenApi adminGroup() {
        return GroupedOpenApi.builder()
            .group("어드민 API")
            .pathsToMatch("/admin/**")
            .build();
    }

    @Bean
    @ConditionalOnProperty(name = "authentication.version", havingValue = "session")
    public OpenAPI openAPI() {
        Info info = new Info()
            .title("NPS 데모 프로젝트")
            .version("ALPHA 20230519")
            .description("Network production system API Document");
        return new OpenAPI()
            .components(new Components())
            .info(info);
    }

    @Bean
    @ConditionalOnProperty(name = "authentication.version", havingValue = "token")
    public OpenAPI openTokenAPI() {
        Info info = new Info()
            .title("NPS 데모 프로젝트")
            .version("ALPHA 20230519")
            .description("Network production system API Document");

        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt); // 헤더에 토큰 포함
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
            .name(jwt)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
        );


        return new OpenAPI()
            .components(components)
            .addSecurityItem(securityRequirement)
            .info(info);
    }
}