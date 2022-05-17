package com.iktpreobuka.egradebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
		"com.iktpreobuka.egradebook"})
public class EGradeBookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EGradeBookBackendApplication.class, args);
	}

}
