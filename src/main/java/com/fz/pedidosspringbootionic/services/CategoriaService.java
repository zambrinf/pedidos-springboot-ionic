package com.fz.pedidosspringbootionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fz.pedidosspringbootionic.domain.Categoria;
import com.fz.pedidosspringbootionic.repositories.CategoriaRepository;
import com.fz.pedidosspringbootionic.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id)); 
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); // para garantir que seja um objeto novo
		return repository.save(obj);
	}
}
