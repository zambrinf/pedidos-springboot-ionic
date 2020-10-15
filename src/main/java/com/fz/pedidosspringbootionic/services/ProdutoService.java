package com.fz.pedidosspringbootionic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fz.pedidosspringbootionic.domain.Produto;
import com.fz.pedidosspringbootionic.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto findById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	
}
