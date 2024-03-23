package com.apiempresausers.apiempresausers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiEmpresaUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEmpresaUsersApplication.class, args);
	}

}