package com.projetoautomacao.drogaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.Fabricante;
import com.projetoautomacao.drogaria.repository.FabricanteRepository;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class FabricanteService {
	
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	public Optional<Fabricante> buscar(Integer id) {
		Optional<Fabricante> obj = fabricanteRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + Fabricante.class.getName());
		}
		return obj;
	}

}
