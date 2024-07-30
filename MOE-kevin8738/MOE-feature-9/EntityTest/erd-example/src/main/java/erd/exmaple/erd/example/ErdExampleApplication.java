package erd.exmaple.erd.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
<<<<<<< HEAD
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
=======

@SpringBootApplication
@EnableJpaAuditing
>>>>>>> 2a1b47c53e50be52577f77cffbbd6e9bd293ba33
public class ErdExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErdExampleApplication.class, args);
	}

}
