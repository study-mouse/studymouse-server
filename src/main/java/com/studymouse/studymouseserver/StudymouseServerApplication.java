package com.studymouse.studymouseserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudymouseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudymouseServerApplication.class, args);
	}

}
