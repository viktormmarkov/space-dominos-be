package com.viktormmarkov.spacedominos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SpaceDominosServer {

	public static void main(String[] args) {
		SpringApplication.run(SpaceDominosServer.class, args);
	}

}
