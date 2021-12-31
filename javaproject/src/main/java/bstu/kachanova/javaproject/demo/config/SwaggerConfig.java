package bstu.kachanova.javaproject.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Look, I've changed!!!!")
                                .version("6.6.6")
                                .contact(
                                        new Contact()
                                                .email("nastiy0205@gmail.com")
                                                .name("Kachanova Anastasia")
                                )
                );
    }
}
