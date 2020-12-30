package com.projetoautomacao.drogaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.Produto;
import com.projetoautomacao.drogaria.repository.ProdutoRepository;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<Produto> buscar(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + Produto.class.getName());
		}
		return obj;
	}

}
