package com.llccing.livestream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class LiveStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveStreamApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner (ApplicationContext ctx) {
        return args -> {
            System.out.println("let's inspect the beans provided by llccing");
        };
    }
}
