package com.fu.isyeri.services.abstracts;

import java.util.List;

import com.fu.isyeri.entities.Company;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;

public interface CompanyService {
	DataResult<List<Company>> getAll();

	Result add(Company company);
}
