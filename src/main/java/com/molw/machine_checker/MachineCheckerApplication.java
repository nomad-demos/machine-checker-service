package com.molw.machine_checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MachineCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachineCheckerApplication.class, args);
	}

}
