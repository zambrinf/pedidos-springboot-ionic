package com.fz.pedidosspringbootionic.config;

import com.fz.pedidosspringbootionic.services.DBService;
import com.fz.pedidosspringbootionic.services.email.EmailService;
import com.fz.pedidosspringbootionic.services.email.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockMailService();
    }
}
