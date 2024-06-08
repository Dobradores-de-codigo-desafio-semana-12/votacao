package com.example.Funcionario.configuracoes;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class ActuatorConfig {
}
