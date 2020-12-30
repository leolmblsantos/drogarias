package com.projetoautomacao.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoautomacao.drogaria.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
