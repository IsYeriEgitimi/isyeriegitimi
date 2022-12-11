package com.fu.isyeri.services.concretes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fu.isyeri.entities.Company;
import com.fu.isyeri.enums.FileEnum;
import com.fu.isyeri.repository.CompanyRepository;
import com.fu.isyeri.repository.ProtocolRepository;
import com.fu.isyeri.result.DataResult;
import com.fu.isyeri.result.Result;
import com.fu.isyeri.services.abstracts.CompanyService;

@Service
public class CompanyManager implements CompanyService{

	private CompanyRepository companyRepository;
	private FileManager fileManager;
	
	public CompanyManager(CompanyRepository companyRepository, FileManager fileManager, ProtocolRepository protocolRepository) {
		this.companyRepository = companyRepository;
		this.fileManager = fileManager;
	}
	
	@Override
	public DataResult<Page<Company>> getAll(Pageable page) {
		return new DataResult<Page<Company>>(companyRepository.findAll(page), true, "Şirket listelendi");
	}

	@Override
	public Result add(Company company) {
		if (company.getImage() != null) {
			try {		
				String storedImageName = fileManager.writeBase64EncodedStringtoFile(company.getImage(), FileEnum.Image);
				company.setImage(storedImageName);
			} catch (Exception e) {}
		}
		if (company.getProtocol() != null) {
			try {
				String storedProtocolName = fileManager.writeBase64EncodedStringtoFile(company.getProtocol().getProtocolName(), FileEnum.Protocol);	
				company.getProtocol().setProtocolName(storedProtocolName);			
			} catch (Exception e) {}
		}
		companyRepository.save(company);
		return new Result(true, "Şirket eklendi");
	}

	@Override
	@Transactional
	public Result delete(int id) {
		Company company = companyRepository.findById(id).get();
		fileManager.deleteAllStoredFileForCompany(company);
		companyRepository.deleteById(id);
		return new Result(true, "Şirket silindi");
	}

	@Override
	public Company update(int id, Company updateCompany){
		Company company = companyRepository.findById(id).get();
		
		company.setCompanyEmail(updateCompany.getCompanyEmail());
		company.setCompanyName(updateCompany.getCompanyName());
		company.setCompanyPhone(updateCompany.getCompanyPhone());
		if (updateCompany.getAddress() != null) {
			company.getAddress().setProvince(updateCompany.getAddress().getProvince());
			company.getAddress().setDistrict(updateCompany.getAddress().getDistrict());
			company.getAddress().setNeighborhood(updateCompany.getAddress().getNeighborhood());
			company.getAddress().setRoad(updateCompany.getAddress().getRoad());
			company.getAddress().setNo(updateCompany.getAddress().getNo());
		}
		
		
		
		if (updateCompany.getImage() != null) {
			try {			
				String oldCompanyImage = company.getImage();
				String storedImageName = fileManager.writeBase64EncodedStringtoFile(updateCompany.getImage(), FileEnum.Image);
				company.setImage(storedImageName);
				
				if(oldCompanyImage != null) {				
					fileManager.deleteCompanyFile(oldCompanyImage, FileEnum.Image);
				}
			} catch (Exception e) {}
			
		}
				
		if (updateCompany.getProtocol() != null) {
			try {
				String oldCompanyProtocolName = company.getProtocol().getProtocolName();				
				String storedProtocolName = fileManager.writeBase64EncodedStringtoFile(updateCompany.getProtocol().getProtocolName(), FileEnum.Protocol);	
				if (company.getProtocol() == null) {
					company.setProtocol(updateCompany.getProtocol());
				}else {
					company.getProtocol().setProtocolName(storedProtocolName);		
					company.getProtocol().setProtocolFileType(updateCompany.getProtocol().getProtocolFileType());
				}
				if (oldCompanyProtocolName != null) {				
					fileManager.deleteCompanyFile(oldCompanyProtocolName, FileEnum.Protocol);
				}
			} catch (Exception e) {}
			
		}
		return companyRepository.save(company);
	}

}
