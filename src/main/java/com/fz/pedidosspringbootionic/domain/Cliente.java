package com.fz.pedidosspringbootionic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fz.pedidosspringbootionic.domain.enums.Perfil;
import com.fz.pedidosspringbootionic.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@Column(unique = true) //emails não podem repetir
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;

	@JsonIgnore
	private String senha;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private final List<Endereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private final Set<String> telefones = new HashSet<>();

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private final Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private final List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo); // alterado
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod(); //alterado
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream()
				.map(perfilId -> Perfil.toEnum(perfilId))
				.collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil){
		this.perfis.add(perfil.getCod());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}


}
