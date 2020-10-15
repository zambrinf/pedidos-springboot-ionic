package com.fz.pedidosspringbootionic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fz.pedidosspringbootionic.domain.Categoria;
import com.fz.pedidosspringbootionic.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	
}
