package com.projetoautomacao.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoautomacao.drogaria.model.PrincipioAtivo;

@Repository
public interface PrincipioAtivoRepository extends JpaRepository<PrincipioAtivo, Integer> {

}
