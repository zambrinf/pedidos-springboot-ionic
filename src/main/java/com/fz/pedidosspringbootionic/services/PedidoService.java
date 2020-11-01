package com.fz.pedidosspringbootionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fz.pedidosspringbootionic.domain.Pedido;
import com.fz.pedidosspringbootionic.repositories.PedidoRepository;
import com.fz.pedidosspringbootionic.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id)); 
	}
	
	public List<Pedido> findAll() {
		return repository.findAll();
	}

}
