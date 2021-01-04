package com.projetoautomacao.drogaria.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoautomacao.drogaria.dto.PrincipioAtivoDTO;
import com.projetoautomacao.drogaria.model.PrincipioAtivo;
import com.projetoautomacao.drogaria.service.PrincipioAtivoService;

@RestController
@RequestMapping(value = "/principioAtivos")
public class PrincipioAtivoController {
	
	@Autowired
	private PrincipioAtivoService principioAtivoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PrincipioAtivo> find(@PathVariable Integer id) {
		PrincipioAtivo obj = principioAtivoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PrincipioAtivoDTO objDto) {
		PrincipioAtivo obj = principioAtivoService.fromDTO(objDto);
		obj = principioAtivoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PrincipioAtivoDTO objDto, @PathVariable Integer id) {
		PrincipioAtivo obj = principioAtivoService.fromDTO(objDto);
		obj.setId(id);
		obj = principioAtivoService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		principioAtivoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PrincipioAtivoDTO>> findAll() {
		List<PrincipioAtivo> list = principioAtivoService.findAll();
		List<PrincipioAtivoDTO> listDto = list.stream().map(obj -> new PrincipioAtivoDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	/*Buscar dados por paginação - */
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PrincipioAtivoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<PrincipioAtivo> list = principioAtivoService.findPage(page, linesPerPage, orderBy, direction);
		Page<PrincipioAtivoDTO> listDto = list.map(obj -> new PrincipioAtivoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}
