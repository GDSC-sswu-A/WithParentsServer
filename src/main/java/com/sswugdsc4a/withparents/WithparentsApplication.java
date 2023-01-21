package com.sswugdsc4a.withparents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WithparentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithparentsApplication.class, args);
    }

}
