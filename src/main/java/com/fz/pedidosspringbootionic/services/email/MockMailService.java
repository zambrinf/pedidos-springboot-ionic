package com.fz.pedidosspringbootionic.services.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockMailService extends AbstractEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockMailService.class);


    @Override
    public void sendEmail(SimpleMailMessage smm) {
        LOGGER.info("Simulando envio de email...");
        LOGGER.info(smm.toString());
        LOGGER.info("Enviado com sucesso");
    }

}
