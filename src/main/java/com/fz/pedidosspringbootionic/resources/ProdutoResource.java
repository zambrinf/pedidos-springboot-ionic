package com.fz.pedidosspringbootionic.resources;

import com.fz.pedidosspringbootionic.domain.Produto;
import com.fz.pedidosspringbootionic.dto.ProdutoDTO;
import com.fz.pedidosspringbootionic.resources.utils.URL;
import com.fz.pedidosspringbootionic.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue = "") String nome,
			@RequestParam(value="categorias", defaultValue = "") String categorias,
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, // 24 pq é multiplo 1,2,3,4
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> categoriasIds = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, categoriasIds, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listDto);
	}
	
}
