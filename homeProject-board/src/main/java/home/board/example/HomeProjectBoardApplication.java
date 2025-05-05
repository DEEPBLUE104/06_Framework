package home.board.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class HomeProjectBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeProjectBoardApplication.class, args);
	}

}
