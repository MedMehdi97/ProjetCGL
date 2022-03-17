package com.app.projetcgl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Document API", version = "3.0"))
public class ProjetCglApplication {

    public static void main(String[] args) {
		SpringApplication.run(ProjetCglApplication.class, args);
	}

}
