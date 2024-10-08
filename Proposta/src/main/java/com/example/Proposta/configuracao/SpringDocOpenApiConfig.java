package com.example.Proposta.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Rest API - Spring Propostas e Votos")
                                .description("API para Salvar Propostas e Realizar Votação.\n Contatos: \n Vagner Ferreira - vagner.lima.pb@compasso.com.br, \n Juliana Ransani - juliana.ransani.pb@compasso.com.br, \n Jeferson Gomes - jeferson.gomes.pb@compasso.com.br")
                                .version("v1")
                                .license(new License().name("Apache2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }
}
