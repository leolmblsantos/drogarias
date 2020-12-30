package com.projetoautomacao.drogaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.PrincipioAtivo;
import com.projetoautomacao.drogaria.repository.PrincipioAtivoRepository;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class PrincipioAtivoService {
	
	@Autowired
	private PrincipioAtivoRepository principioAtivoRepository;
	
	public Optional<PrincipioAtivo> buscar(Integer id) {
		Optional<PrincipioAtivo> obj = principioAtivoRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + PrincipioAtivo.class.getName());
		}
		return obj;
	}

}
