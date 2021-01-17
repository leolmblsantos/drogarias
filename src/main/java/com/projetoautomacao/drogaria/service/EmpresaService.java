package com.projetoautomacao.drogaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projetoautomacao.drogaria.dto.EmpresaDTO;
import com.projetoautomacao.drogaria.model.Empresa;
import com.projetoautomacao.drogaria.repository.EmpresaRepository;
import com.projetoautomacao.drogaria.service.exceptions.DataIntegrityException;
import com.projetoautomacao.drogaria.service.exceptions.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Empresa find(Integer id) {
		Optional<Empresa> obj = empresaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Empresa.class.getName()));
	}
	
	public Empresa insert(Empresa obj) {
		obj.setId(null);
		return empresaRepository.save(obj);
	}
	
	public Empresa update(Empresa obj) {
		Empresa newObj = find(obj.getId());
		updateData(newObj, obj);
		return empresaRepository.save(newObj);
	}
		
	/* ---- Serviço de delete -------- */
	public void delete(Integer id) {
		find(id);
		try {
			empresaRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma empresa que possui produtos");
		}
	}
	
	public List<Empresa> findAll() {
		return empresaRepository.findAll();
	}
	
	public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return empresaRepository.findAll(pageRequest);
	}
	
	public Empresa fromDTO(EmpresaDTO objDto) {
		return new Empresa(objDto.getId(), objDto.getNome(), objDto.getCnpj(), objDto.getEmail());
	}
	
	private void updateData(Empresa newObj, Empresa obj) {
		newObj.setNome(obj.getNome());
	}

	
}
