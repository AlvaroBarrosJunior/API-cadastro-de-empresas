package com.cadastrodeempresas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cadastrodeempresas.domain.model.Company;
import com.cadastrodeempresas.domain.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	@GetMapping
	public List<Company> companiesList() {
		return companyService.companiesList();
	}
	
	@GetMapping("/{companyId}")
	public ResponseEntity<Company> searchCompany(@PathVariable Long companyId) {
		ResponseEntity<Company> result = companyService.searchCompany(companyId);
		
		return result;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Company addCompany(@Valid @RequestBody Company company) {
		return companyService.addCompany(company);
	}
	
	@PutMapping("/{companyId}")
	public ResponseEntity<Company> updateCompany(@Valid @PathVariable Long companyId, @Valid @RequestBody Company company) {
		
		ResponseEntity<Company> result = companyService.updateCompany(companyId, company);
		
		return result;
	}
	
	@DeleteMapping("/{companyId}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId){
		
		ResponseEntity<Void> result = companyService.deleteCompany(companyId);
		
		return result;
	}
}
