package com.fu.isyeri.services.abstracts;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fu.isyeri.entities.Company;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;

public interface CompanyService {
	DataResult<Page<Company>> getAll(Pageable pageable);

	Result add(Company company);
	
	Result delete(int id);

	Company update(int id, Company updateCompany);

	DataResult<Page<Company>> findByCompanyName(String companyName, Pageable pageable);
}
