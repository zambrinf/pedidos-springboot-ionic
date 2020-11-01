package com.fz.pedidosspringbootionic.services.email;

import com.fz.pedidosspringbootionic.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

}
