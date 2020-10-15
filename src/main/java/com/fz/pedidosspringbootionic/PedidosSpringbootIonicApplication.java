package com.fz.pedidosspringbootionic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fz.pedidosspringbootionic.domain.Categoria;
import com.fz.pedidosspringbootionic.repositories.CategoriaRepository;

@SpringBootApplication
public class PedidosSpringbootIonicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		
		SpringApplication.run(PedidosSpringbootIonicApplication.class, args);
		
		
		
	}
	
	
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Command Line Runner - na iniciação
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
