package com.cadastrodeempresas.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cadastrodeempresas.domain.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	

}
