package edu.kh.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class BoardProjectHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectHomeApplication.class, args);
	}

}
