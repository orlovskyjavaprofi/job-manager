/******************************************
 Created by: RXSUBRAM a.k.a angoothachap
 Created on: 4/15/2019 11:19 AM
 ******************************************/
package com.orlovsky.java.profi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*  Thymeleaf picks up properties files from under the /templates if it's under /WEB-INF. Since we're using
        SpringBoot, we need to explicitly register the properties files for MVC to pick them up. Hence, declaring
        the messageSource bean to load the properties files*/
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/templates/login");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }
}
