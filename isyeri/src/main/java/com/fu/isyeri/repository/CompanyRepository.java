package com.fu.isyeri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fu.isyeri.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
}
