package ru.altacod.wikiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.altacod.wikiapp.repository")
public class WikiAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikiAppApplication.class, args);
    }

}
