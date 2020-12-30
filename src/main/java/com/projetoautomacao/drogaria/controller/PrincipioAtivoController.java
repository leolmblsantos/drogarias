package com.projetoautomacao.drogaria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projetoautomacao.drogaria.model.PrincipioAtivo;
import com.projetoautomacao.drogaria.service.PrincipioAtivoService;

@RestController
@RequestMapping(value = "/principioAtivos")
public class PrincipioAtivoController {
	
	@Autowired
	private PrincipioAtivoService principioAtivoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Optional<PrincipioAtivo> obj = principioAtivoService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

}
