package com.example.junkfashionbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "JunkFashion API", description = "JunkFashion API", version = "0.0.2"))
public class JunkFashionBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JunkFashionBackendApplication.class, args);
    }

}
