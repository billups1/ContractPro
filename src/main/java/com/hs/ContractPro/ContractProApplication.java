package com.hs.ContractPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ContractProApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractProApplication.class, args);
	}

}
