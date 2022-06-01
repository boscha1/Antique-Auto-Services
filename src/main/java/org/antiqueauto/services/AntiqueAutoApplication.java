package org.antiqueauto.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// test to see if pipeline runs

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AntiqueAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntiqueAutoApplication.class, args);
	}

}
