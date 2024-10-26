package com.hackathon.bankingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAsync
public class BankingappApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BankingappApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() 
	{
		return new RestTemplate();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
}
