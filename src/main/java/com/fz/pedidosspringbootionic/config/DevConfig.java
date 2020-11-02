package com.fz.pedidosspringbootionic.config;

import com.fz.pedidosspringbootionic.services.DBService;
import com.fz.pedidosspringbootionic.services.email.EmailService;
import com.fz.pedidosspringbootionic.services.email.MockMailService;
import com.fz.pedidosspringbootionic.services.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.text.ParseException;
import java.util.Collections;
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

        if(!strategy.equals("create")) {
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);

        mailSender.setUsername("98f35e489235a9");
        mailSender.setPassword("8a71cf4c84711c");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
