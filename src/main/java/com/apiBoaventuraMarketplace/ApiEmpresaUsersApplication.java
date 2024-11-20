package com.apiBoaventuraMarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ApiEmpresaUsersApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		System.setProperty("spring.datasource.url", dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("spring.datasource.username", dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("spring.datasource.password", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
		System.setProperty("spring.datasource.driver-class-name", dotenv.get("SPRING_JPA_PROPERTIES_DRIVER_CLASS_NAME"));
		System.setProperty("spring.jpa.properties.hibernate.dialect", dotenv.get("SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT"));
		System.setProperty("jwtSecret", dotenv.get("jwt.secret"));

		SpringApplication.run(ApiEmpresaUsersApplication.class, args);
	}
}
