package com.infosys.capacitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CapacitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapacitorApplication.class, args);
	}

}
