package com.projetoautomacao.drogaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.model.Usuario;
import com.projetoautomacao.drogaria.repository.UsuarioRepository;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscar(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

}
