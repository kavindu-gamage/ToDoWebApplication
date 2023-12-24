// package com.example.todo.swagger;

// import org.springdoc.core.models.GroupedOpenApi;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.models.ExternalDocumentation;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.info.License;

// @Configuration
// public class SwaggerSecurityConfig {
// @Bean
// public GroupedOpenApi publicApi() {
// return GroupedOpenApi.builder()
// .group("springshop-public")
// .pathsToMatch("/public/**")
// .build();
// }

// @Bean
// public GroupedOpenApi adminApi() {
// return GroupedOpenApi.builder()
// .group("springshop-admin")
// .pathsToMatch("/admin/**")
// .addOpenApiMethodFilter(method ->
// method.isAnnotationPresent(OpenAPIDefinition.class))
// .build();
// }

// @Bean
// public OpenAPI springShopOpenAPI() {
// return new OpenAPI()
// .info(new Info().title("SpringShop API")
// .description("Spring shop sample application")
// .version("v0.0.1")
// .license(new License().name("Apache 2.0").url("http://springdoc.org")))
// .externalDocs(new ExternalDocumentation()
// .description("SpringShop Wiki Documentation")
// .url("https://springshop.wiki.github.org/docs"));
// }
// }
