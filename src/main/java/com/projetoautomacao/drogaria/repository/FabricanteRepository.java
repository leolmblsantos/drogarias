package com.projetoautomacao.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoautomacao.drogaria.model.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {

}
