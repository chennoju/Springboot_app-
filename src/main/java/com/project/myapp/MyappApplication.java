package com.project.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}
	
	@Bean
	public RestTemplate createtemplate() {
		System.out.println("running");
		return new RestTemplate();
	}
	
	
	@Bean
	public WebClient.Builder createBuilder() {
		System.out.println("running");
		return  WebClient.builder();
	}
	

}
