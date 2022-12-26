package com.fu.isyeri.controllers;


import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fu.isyeri.entities.Company;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.CompanyService;

@RestController
@RequestMapping("/api/1.0/company")
public class CompanyController {
	
	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/getAll")
	public DataResult<Page<Company>> getAll(Pageable pageable){
		return companyService.getAll(pageable);
	}
	
	@GetMapping("/getCompany/{companyName}")
	public DataResult<Page<Company>> findByCompanyName (@PathVariable String companyName, Pageable pageable){
		return companyService.findByCompanyName(companyName, pageable);
	}
	
	@PostMapping("/add")
	@PreAuthorize("principal.role.level == 2")
	public Result add(@Valid @RequestBody Company company) {
		return companyService.add(company);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("principal.role.level == 2")
	public Result delete(@PathVariable int id) {
		return companyService.delete(id);
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("principal.role.level == 2")
	public DataResult<Company> update(@Valid @RequestBody Company updateCompany, @PathVariable int id) {
		Company company = companyService.update(id, updateCompany);
		return new DataResult<Company>(company, true, "Şirket güncellendi");
	}
	
}
