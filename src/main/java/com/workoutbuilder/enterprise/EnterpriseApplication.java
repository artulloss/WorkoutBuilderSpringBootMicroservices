package com.workoutbuilder.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Entry point for the Workout Builder application.
 */
@SpringBootApplication
@EnableCaching
public class EnterpriseApplication {

	/**
	 * Starts the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EnterpriseApplication.class, args);
	}
}
