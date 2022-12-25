package com.fu.isyeri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fu.isyeri.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	Page<Company> findByCompanyNameContains(String companyName, Pageable pageable);
}
