package br.com.fiap.sprintjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SprintJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintJavaApplication.class, args);
    }

}
