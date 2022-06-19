package com.balram.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PractiseApplication {
	
	@Autowired EmployeeRepository repository;
	
	@Bean
	CommandLineRunner initDatabase() {

		return args -> {
			System.out.println("Preloading " + repository.save(new Employee("Balram", "Cheryala")));
			System.out.println("Preloading " + repository.save(new Employee("Examine", "Developer")));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PractiseApplication.class, args);
	}

}
