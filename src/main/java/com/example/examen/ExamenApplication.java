package com.example.examen;

import com.example.examen.rest.repositories.VotoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExamenApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ExamenApplication.class, args);
                VotoRepository data = context.getBean(VotoRepository.class);
	}

}
