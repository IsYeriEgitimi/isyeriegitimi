package com.fu.isyeri.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.entities.Company;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.CompanyService;

@RestController
@RequestMapping(name = "/api/1.0/company")
public class CompanyController {

	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Company>> getAll(){
		return companyService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Company company) {
		return companyService.add(company);
	}
	
}
