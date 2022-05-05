package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info( title = "Yogisera", version = "1.0", 
		description= "Backend site for yoga lovers to view,manage and trade course and their relevent entities information "))
public class YogiseraSpringboot {

	public static void main(String[] args) {
		SpringApplication.run(YogiseraSpringboot.class, args);
	}

}
