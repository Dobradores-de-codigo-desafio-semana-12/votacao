package com.example.Funcionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

;


@SpringBootApplication

public class FuncionarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuncionarioApplication.class, args);
	}

}
