package com.example.Funcionario.configuracao;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class ActuatorConfig {
}
