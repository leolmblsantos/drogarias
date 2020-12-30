package com.projetoautomacao.drogaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.Categoria;
import com.projetoautomacao.drogaria.repository.CategoriaRepository;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> buscar(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

}
