package com.example.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
					.route(r ->r.path("/api/v1/funcionarios/**").uri("lb://Funcionario"))
					.route(r ->r.path("/api/v1/propostas/**").uri("lb://Proposta"))
					.route(r ->r.path("/api/v1/votos/**").uri("lb://Proposta"))
				.build();
	}

}
