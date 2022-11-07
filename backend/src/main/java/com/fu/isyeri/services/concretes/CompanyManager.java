package com.fu.isyeri.services.concretes;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fu.isyeri.entities.Company;
import com.fu.isyeri.repository.CompanyRepository;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.CompanyService;

@Service
public class CompanyManager implements CompanyService{

	private CompanyRepository companyRepository;
	
	public CompanyManager(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Override
	public DataResult<List<Company>> getAll() {
		return new DataResult<List<Company>>(companyRepository.findAll(), true, "Şirket listelendi");
	}

	@Override
	public Result add(Company company) {
		companyRepository.save(company);
		return new Result(true, "Şirket eklendi");
	}

	@Override
	public Result delete(int id) {
		companyRepository.deleteById(id);
		return new Result(true, "Şirket silindi");
	}

}
