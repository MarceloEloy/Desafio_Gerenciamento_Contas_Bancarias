package com.example.DesafioGerenciamentoContasBancarias.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI().info(new Info()
                .description("Web API com foco no gerenciamento de operações bancarias")
                .version("v1")
                .title("Desafio Gerenciamento Contas Bancarias")
        );

    }

}
