package com.fu.isyeri.services.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.fu.isyeri.entities.Company;
import com.fu.isyeri.repository.ProtocolRepository;
import com.fu.isyeri.strategies.abstracts.FileTypeStrategy;
import com.fu.isyeri.strategies.concretes.ImageFileTypeStrategy;
import com.fu.isyeri.strategies.concretes.ProtocolFileTypeStrategy;

@Service
public class FileManager {
	
	ProtocolRepository protocolRepository;
	
	public FileManager(ProtocolRepository protocolRepository) {
		this.protocolRepository = protocolRepository;
	}

	public String writeBase64EncodedStringtoFile(String file, FileTypeStrategy fileTypeStrategy) throws IOException {
		String fileName = generateRandomName();
		
		if (fileTypeStrategy.getClass() == ProtocolFileTypeStrategy.class) {			
			fileName += ".pdf";
		}
		
		File target = new File(fileTypeStrategy.getStoragePath()+"/"+fileName);
		
		
		
		try {
			OutputStream outputStream = new FileOutputStream(target);
			byte[] base64Encoded = Base64.getDecoder().decode(file);
			outputStream.write(base64Encoded);
			outputStream.close();
		}
		catch (Exception e) { System.out.println("FileManager HATA: "+e); }
		
		return fileName;
	}
		

	public String generateRandomName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public void deleteCompanyFile(String file, FileTypeStrategy fileTypeStrategy) {
		try {
			Files.deleteIfExists(Paths.get(fileTypeStrategy.getStoragePath(), file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteAllStoredFileForCompany(Company company) {
		
		if (company.getImage() != null) {
			deleteCompanyFile(company.getImage(), new ImageFileTypeStrategy());
		}
		if(company.getProtocol() != null) {
			deleteCompanyFile(company.getProtocol().getProtocolName(), new ProtocolFileTypeStrategy());
		}
		
	}

}
