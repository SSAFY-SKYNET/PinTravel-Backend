package com.ssafy.xmagazine.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("XMAGAZINE API").description("XMAGAZINE API 문서").version("v1");

        return new OpenAPI().components(new Components()).info(info);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-user")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("v1-admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi magazineApi() {
        return GroupedOpenApi.builder()
                .group("v1-magazine")
                .pathsToMatch("/board/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commentApi() {
        return GroupedOpenApi.builder()
                .group("v1-comment")
                .pathsToMatch("/comment/**")
                .build();
    }

    @Bean
    public GroupedOpenApi followersApi() {
        return GroupedOpenApi.builder()
                .group("v1-followers")
                .pathsToMatch("/followers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi likesApi() {
        return GroupedOpenApi.builder()
                .group("v1-likes")
                .pathsToMatch("/likes/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pinApi() {
        return GroupedOpenApi.builder()
                .group("v1-pin")
                .pathsToMatch("/pin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pinboardsApi() {
        return GroupedOpenApi.builder()
            .group("v1-pinboards")
            .pathsToMatch("/pinboards/**")
            .build();
    }

    @Bean
    public GroupedOpenApi pintagsApi() {
        return GroupedOpenApi.builder()
            .group("v1-pintags")
            .pathsToMatch("/pintags/**")
            .build();
    }

    @Bean
    public GroupedOpenApi tagsApi() {
        return GroupedOpenApi.builder()
            .group("v1-tags")
            .pathsToMatch("/tags/**")
            .build();
    }
}
