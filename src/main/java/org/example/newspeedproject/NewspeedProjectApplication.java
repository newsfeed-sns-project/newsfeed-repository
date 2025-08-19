package org.example.newspeedproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NewspeedProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewspeedProjectApplication.class, args);
    }

}
