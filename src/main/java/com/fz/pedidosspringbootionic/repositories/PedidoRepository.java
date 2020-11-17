package com.fz.pedidosspringbootionic.repositories;

import com.fz.pedidosspringbootionic.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fz.pedidosspringbootionic.domain.Pedido;

import javax.transaction.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

    @Transactional
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);

}
