package com.balram.development;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.balram.development.entity.Employee;
import com.balram.development.entity.SupplierItemIdentifier;
import com.balram.development.repo.EmployeeRepository;
import com.balram.development.repo.PerformanceRepo;

@SpringBootApplication
public class PractiseApplication {
	
	@Autowired EmployeeRepository repository;
	@Autowired PerformanceRepo performanceRepo;
	
	@Bean
	CommandLineRunner initDatabase() {

		return args -> {
			System.out.println("Started");
			Instant start = Instant.now();
			loop();
			Instant end = Instant.now();
			System.out.println("Ended in seconds " + Duration.between(start, end).getSeconds());
		};
	}
	
	private void loop(){
		for(int i=0; i < 200000; i++) {
			System.out.println("Preloading " + repository.save(new Employee("Examine", "Developer" + i)));
			System.out.println(" Persormance : "+ performanceRepo.save(new SupplierItemIdentifier("buType"+i,"b"+i,"c"+i,"d"+i,"e"+i)));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(PractiseApplication.class, args);
	}

}
