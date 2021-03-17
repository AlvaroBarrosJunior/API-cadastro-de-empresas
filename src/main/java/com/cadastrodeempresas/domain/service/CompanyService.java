package com.cadastrodeempresas.domain.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cadastrodeempresas.domain.model.Company;
import com.cadastrodeempresas.util.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> companiesList() {
		return companyRepository.findAll();
	}
	
	public ResponseEntity<Company> searchCompany(@PathVariable Long companyId) {
		Optional<Company> company = companyRepository.findById(companyId);
		
		if (company.isPresent()) {
			return ResponseEntity.ok(company.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public Company addCompany(@Valid @RequestBody Company company) {
		return companyRepository.save(company);
	}
	
	public ResponseEntity<Company> updateCompany(@Valid @PathVariable Long companyId, @Valid @RequestBody Company company) {
		
		if (!companyRepository.existsById(companyId)) {
			return ResponseEntity.notFound().build();
		}
		
		company.setId(companyId);
		company = companyRepository.save(company);
		
		return ResponseEntity.ok(company);
	}
	
	public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId){
		
		if (!companyRepository.existsById(companyId)) {
			return ResponseEntity.notFound().build();
		}
		
		companyRepository.deleteById(companyId);
		
		return ResponseEntity.noContent().build();
	}

}
