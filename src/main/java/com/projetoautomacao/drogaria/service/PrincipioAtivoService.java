package com.projetoautomacao.drogaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.dto.PrincipioAtivoDTO;
import com.projetoautomacao.drogaria.model.PrincipioAtivo;
import com.projetoautomacao.drogaria.repository.PrincipioAtivoRepository;
import com.projetoautomacao.drogaria.service.exceptions.DataIntegrityException;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class PrincipioAtivoService {
	
	@Autowired
	private PrincipioAtivoRepository principioAtivoRepository;
	
	public PrincipioAtivo find(Integer id) {
		Optional<PrincipioAtivo> obj = principioAtivoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PrincipioAtivo.class.getName()));
	}
	
	public PrincipioAtivo insert(PrincipioAtivo obj) {
		obj.setId(null);
		return principioAtivoRepository.save(obj);
	}
	
	public PrincipioAtivo update(PrincipioAtivo obj) {
		PrincipioAtivo newObj = find(obj.getId());
		updateData(newObj, obj);
		return principioAtivoRepository.save(newObj);
	}
		
	/* ---- Serviço de delete -------- */
	public void delete(Integer id) {
		find(id);
		try {
			principioAtivoRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma principioAtivo que possui produtos");
		}
	}
	
	public List<PrincipioAtivo> findAll() {
		return principioAtivoRepository.findAll();
	}
	
	public Page<PrincipioAtivo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return principioAtivoRepository.findAll(pageRequest);
	}
	
	public PrincipioAtivo fromDTO(PrincipioAtivoDTO objDto) {
		return new PrincipioAtivo(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(PrincipioAtivo newObj, PrincipioAtivo obj) {
		newObj.setNome(obj.getNome());
	}

}
