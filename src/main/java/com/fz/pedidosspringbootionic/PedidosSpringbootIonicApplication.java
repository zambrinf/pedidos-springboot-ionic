package com.fz.pedidosspringbootionic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fz.pedidosspringbootionic.domain.Categoria;
import com.fz.pedidosspringbootionic.domain.Cidade;
import com.fz.pedidosspringbootionic.domain.Cliente;
import com.fz.pedidosspringbootionic.domain.Endereco;
import com.fz.pedidosspringbootionic.domain.Estado;
import com.fz.pedidosspringbootionic.domain.Produto;
import com.fz.pedidosspringbootionic.domain.enums.TipoCliente;
import com.fz.pedidosspringbootionic.repositories.CategoriaRepository;
import com.fz.pedidosspringbootionic.repositories.CidadeRepository;
import com.fz.pedidosspringbootionic.repositories.ClienteRepository;
import com.fz.pedidosspringbootionic.repositories.EnderecoRepository;
import com.fz.pedidosspringbootionic.repositories.EstadoRepository;
import com.fz.pedidosspringbootionic.repositories.ProdutoRepository;

@SpringBootApplication
public class PedidosSpringbootIonicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		
		SpringApplication.run(PedidosSpringbootIonicApplication.class, args);

	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//Command Line Runner - na iniciação - Mock
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail", "33333333333", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("33222335","33223322"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "100", "ap 101", "jardim", "38241456", cli1, c1);
		Endereco e2 = new Endereco(null, "av matos", "102", "sala 501", "Centro", "3423432", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
