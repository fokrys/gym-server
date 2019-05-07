package com.fokrys.gym;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", maxAge = 3600)
@CrossOrigin("*")
public class GymApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GymApplication.class, args);
	}
	
	 
}
