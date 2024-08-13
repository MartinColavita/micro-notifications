package com.eldar.micronotifications.config.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi mailApi() {
        return GroupedOpenApi.builder()
                .group("Mail Notifications")
                .packagesToScan("com.eldar.micronotifications.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi whatsappApi() {
        return GroupedOpenApi.builder()
                .group("Whatsapp Notifications")
                .packagesToScan("com.eldar.micronotifications.controller")
                .pathsToMatch("/api/wsp/**")
                .build();
    }

    @Bean
    public GroupedOpenApi smsApi() {
        return GroupedOpenApi.builder()
                .group("SMS Notifications")
                .packagesToScan("com.eldar.micronotifications.controller")
                .pathsToMatch("/api/sms/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Micro Notifications API")
                        .version("1.0")
                        .description("API para envio de notificaciones por Whatsapp, SMS y Mail")
                        .contact(new Contact()
                                .name("Martin Colavita")
                                .email("martincolavita@eldars.com.ar")));
    }

}
