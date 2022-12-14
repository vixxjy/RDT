package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RdtApplication {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(RdtApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			userService.createUser(new User(1L, "admin@admin.com", "password", "admin"));
		};
	}

}
