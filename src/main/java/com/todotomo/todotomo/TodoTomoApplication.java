package com.todotomo.todotomo;

import com.todotomo.todotomo.domain.task.TaskState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class TodoTomoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoTomoApplication.class, args);
	}

}
