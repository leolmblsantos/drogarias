package com.projetoautomacao.drogaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.dto.CidadeDTO;
import com.projetoautomacao.drogaria.model.Cidade;
import com.projetoautomacao.drogaria.repository.CidadeRepository;
import com.projetoautomacao.drogaria.service.exceptions.DataIntegrityException;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> findByEstado(Integer estadoId) {
		return cidadeRepository.findCidades(estadoId);
	}
	
	public Optional<Cidade> find(Integer id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Ojeto não encontrado! Id: " + id
					+ ", Tipo: " + Cidade.class.getName());
		}
		return obj;
	}
	
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return cidadeRepository.save(obj);
	}
	
	public Cidade update(Cidade obj) {
		find(obj.getId());
		return cidadeRepository.save(obj);
	}

	/*Serviço de delete*/
	public void delete(Integer id) {
		find(id);
		try {
			cidadeRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma estado que possui produtos");
		}
	}
	
	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}
	
	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cidadeRepository.findAll(pageRequest);
	}
	
	public Cidade frmDTO(CidadeDTO objDto) {
		return  new Cidade(objDto.getId(), objDto.getNome(), objDto.getEstado());
	}
}
