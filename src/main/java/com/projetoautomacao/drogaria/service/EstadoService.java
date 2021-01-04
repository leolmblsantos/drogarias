package com.projetoautomacao.drogaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.dto.EstadoDTO;
import com.projetoautomacao.drogaria.model.Estado;
import com.projetoautomacao.drogaria.repository.EstadoRepository;
import com.projetoautomacao.drogaria.service.exceptions.DataIntegrityException;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Optional<Estado> find(Integer id) {
		Optional<Estado> obj = estadoRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + Estado.class.getName());
		}
		return obj;
	}
	
	public Estado insert(Estado obj) {
		obj.setId(null);
		return estadoRepository.save(obj);
	}
	
	public Estado update(Estado obj) {
		find(obj.getId());
		return estadoRepository.save(obj);
	}

	/*Serviço de delete*/
	public void delete(Integer id) {
		find(id);
		try {
			estadoRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma estado que possui produtos");
		}
	}
	
	public List<Estado> findAll() {
		return estadoRepository.findAll();
	}
	
	public Page<Estado> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return estadoRepository.findAll(pageRequest);
	}
	
	public Estado frmDTO(EstadoDTO objDto) {
		return  new Estado(objDto.getId(), objDto.getNome(), objDto.getUf());
	}
}
