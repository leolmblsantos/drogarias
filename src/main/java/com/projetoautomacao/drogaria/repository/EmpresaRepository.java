package com.projetoautomacao.drogaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoautomacao.drogaria.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	
//	@Transactional(readOnly=true)
//	@Query("SELECT obj FROM Empresa obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
//	public List<Empresa> findEmpresas(@Param("estadoId") Integer estado_id);
}
