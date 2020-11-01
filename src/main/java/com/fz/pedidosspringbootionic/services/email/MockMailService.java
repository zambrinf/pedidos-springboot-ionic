package com.fz.pedidosspringbootionic.services.email;

import com.fz.pedidosspringbootionic.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MockMailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Value("${default.recipient}")
    private String recipient;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setSubject("Confirmação do Pedido: " + pedido.getId());
        message.setText(pedido.toString());
        emailSender.send(message);
    }

}
