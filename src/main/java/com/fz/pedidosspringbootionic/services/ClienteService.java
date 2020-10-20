package com.fz.pedidosspringbootionic.services;

import com.fz.pedidosspringbootionic.domain.Categoria;
import com.fz.pedidosspringbootionic.domain.Cliente;
import com.fz.pedidosspringbootionic.dto.CategoriaDTO;
import com.fz.pedidosspringbootionic.repositories.ClienteRepository;
import com.fz.pedidosspringbootionic.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id)); 
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}


	
}
