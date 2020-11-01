package com.fz.pedidosspringbootionic.repositories;

import com.fz.pedidosspringbootionic.domain.Cliente;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Cliente findByEmail(String email);

	Cliente findByCpfOuCnpj(String cpfOuCnpj);
}
