package com.fz.pedidosspringbootionic.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fz.pedidosspringbootionic.domain.Produto;
import com.fz.pedidosspringbootionic.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		
		List<Produto> obj = produtoService.findAll();
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		
		Produto obj = produtoService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
}
