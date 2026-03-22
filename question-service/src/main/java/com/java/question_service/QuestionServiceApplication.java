package com.java.question_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
		System.out.println("=========================================");
		System.out.println("Question Service is running on port 8081");
		System.out.println("=========================================");
	}

}
