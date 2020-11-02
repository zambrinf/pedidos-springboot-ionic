package com.fz.pedidosspringbootionic.services.email;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

    @Override
    public void sendEmail(SimpleMailMessage smm) {
        javaMailSender.send(smm);
    }

}
