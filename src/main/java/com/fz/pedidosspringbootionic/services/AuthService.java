package com.fz.pedidosspringbootionic.services;

import com.fz.pedidosspringbootionic.domain.Cliente;
import com.fz.pedidosspringbootionic.repositories.ClienteRepository;
import com.fz.pedidosspringbootionic.services.email.EmailService;
import com.fz.pedidosspringbootionic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }
        String newPassword = newPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPassword);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0 ) {
            return (char) (random.nextInt(10) + 48); // unicode
        } else if (opt == 1 ) {
            return (char) (random.nextInt(26) + 65); // unicode
        } else {
            return (char) (random.nextInt(26) + 97); // unicode
        }
    }

}
