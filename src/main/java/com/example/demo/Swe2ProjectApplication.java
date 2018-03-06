package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("Repository")
public class Swe2ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Swe2ProjectApplication.class, args);
	}
}
