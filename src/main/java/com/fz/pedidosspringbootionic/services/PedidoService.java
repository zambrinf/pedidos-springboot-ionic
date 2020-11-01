package com.fz.pedidosspringbootionic.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fz.pedidosspringbootionic.domain.*;
import com.fz.pedidosspringbootionic.domain.enums.EstadoPagamento;
import com.fz.pedidosspringbootionic.repositories.ItemPedidoRepository;
import com.fz.pedidosspringbootionic.repositories.PagamentoRepository;
import com.fz.pedidosspringbootionic.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fz.pedidosspringbootionic.repositories.PedidoRepository;
import com.fz.pedidosspringbootionic.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailService emailService;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id)); 
	}
	
	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		Cliente cli = clienteService.findById(obj.getCliente().getId());
		obj.setCliente(cli);
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto ) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0d);
			Produto prod = produtoService.findById(ip.getProduto().getId());
			ip.setProduto(prod);
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
