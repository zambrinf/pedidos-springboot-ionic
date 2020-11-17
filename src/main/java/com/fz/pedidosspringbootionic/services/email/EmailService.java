package com.fz.pedidosspringbootionic.services.email;

import com.fz.pedidosspringbootionic.domain.Cliente;
import com.fz.pedidosspringbootionic.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage smm);
    void sendNewPasswordEmail(Cliente cliente, String newPassword);

}
