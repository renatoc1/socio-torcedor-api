package com.api.socioTorcedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.socioTorcedor.model.SocioTorcedor;

@Repository
public interface SocioTorcedorRepository extends JpaRepository<SocioTorcedor, Long>{

	SocioTorcedor findByEmail(String email);

}
