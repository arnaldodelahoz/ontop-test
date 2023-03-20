package com.ontop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class OntopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OntopApplication.class, args);
	}

}
