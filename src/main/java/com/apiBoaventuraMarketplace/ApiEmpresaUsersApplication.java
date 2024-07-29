package com.apiBoaventuraMarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ApiEmpresaUsersApplication {

	public static void main(String[] args) {
		// Carregar o arquivo .env
		Dotenv dotenv = Dotenv.load();

		// Definir as variáveis de ambiente
		System.setProperty("spring.datasource.url", dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("spring.datasource.username", dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("spring.datasource.password", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
		System.setProperty("spring.jpa.hibernate.dialect", dotenv.get("SPRING_JPA_HIBERNATE_DIALECT"));

		SpringApplication.run(ApiEmpresaUsersApplication.class, args);
	}
}
