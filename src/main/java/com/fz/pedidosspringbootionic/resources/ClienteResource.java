package com.fz.pedidosspringbootionic.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fz.pedidosspringbootionic.domain.Cliente;
import com.fz.pedidosspringbootionic.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		
		List<Cliente> obj = service.findAll();
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}