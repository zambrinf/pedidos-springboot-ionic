package com.fz.pedidosspringbootionic.config;

import com.fz.pedidosspringbootionic.services.DBService;
import com.fz.pedidosspringbootionic.services.email.EmailService;
import com.fz.pedidosspringbootionic.services.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.ParseException;
import java.util.Properties;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!strategy.equals("create")) {
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("127.0.0.1");
        mailSender.setPort(1025);
        mailSender.setUsername("");
        mailSender.setPassword("");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
