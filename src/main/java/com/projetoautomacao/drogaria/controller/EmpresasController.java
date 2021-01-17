package com.projetoautomacao.drogaria.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoautomacao.drogaria.dto.EmpresaDTO;
import com.projetoautomacao.drogaria.model.Empresa;
import com.projetoautomacao.drogaria.service.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresasController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Empresa> find(@PathVariable Integer id) {
		
		Empresa obj = empresaService.find(id);
		return ResponseEntity.ok().body(obj);
	}

//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EmpresaDTO objDto) {
		Empresa obj = empresaService.fromDTO(objDto);
		obj = empresaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EmpresaDTO objDto, @PathVariable Integer id) {
		Empresa obj = empresaService.fromDTO(objDto);
		obj.setId(id);
		obj = empresaService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		empresaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EmpresaDTO>> findAll() {
		List<Empresa> list = empresaService.findAll();
		List<EmpresaDTO> listDto = list.stream().map(obj -> new EmpresaDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	/*Buscar dados por paginação - */
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<EmpresaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Empresa> list = empresaService.findPage(page, linesPerPage, orderBy, direction);
		Page<EmpresaDTO> listDto = list.map(obj -> new EmpresaDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
