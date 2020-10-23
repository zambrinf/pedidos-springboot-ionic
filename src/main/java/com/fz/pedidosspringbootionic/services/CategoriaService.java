package com.fz.pedidosspringbootionic.services;

import java.util.List;
import java.util.Optional;

import com.fz.pedidosspringbootionic.domain.Cliente;
import com.fz.pedidosspringbootionic.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fz.pedidosspringbootionic.domain.Categoria;
import com.fz.pedidosspringbootionic.repositories.CategoriaRepository;
import com.fz.pedidosspringbootionic.services.exceptions.DataIntegrityException;
import com.fz.pedidosspringbootionic.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Integer id) {

		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria insert(Categoria obj) {

		obj.setId(null); // para garantir que seja um objeto novo
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

	public void deleteById(Integer id) {

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
				orderBy);
		return repository.findAll(pageRequest); // sobrecarga do metodo findAll
	}

	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
