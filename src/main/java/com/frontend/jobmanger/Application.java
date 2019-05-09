package com.frontend.jobmanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"com.frontend.jobmanager.service", 
	            "com.frontend.jobmanger.controller", 
	            "com.frontend.security.config",
	            "models"})
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
