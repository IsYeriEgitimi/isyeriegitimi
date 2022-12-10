package com.fu.isyeri.services.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import com.fu.isyeri.configuration.AppConfiguration;
import com.fu.isyeri.enums.FileEnum;
import com.fu.isyeri.repository.ProtocolRepository;

@Service
public class FileManager {
	
	ProtocolRepository protocolRepository;
	AppConfiguration appConfiguration;
	Tika tika;
	
	public FileManager(AppConfiguration appConfiguration, ProtocolRepository protocolRepository) {
		this.appConfiguration = appConfiguration;
		this.tika = new Tika();
		this.protocolRepository = protocolRepository;
	}

	public String writeBase64EncodedStringtoFile(String file, FileEnum fileEnum) throws IOException {
		String fileName = generateRandomName();
		File target = (fileEnum == FileEnum.Image) ? 
				new File(appConfiguration.getImageStoragePath()+"/"+fileName) : new File(appConfiguration.getProtocolStoragePath()+"/"+fileName);
		
		try {
			OutputStream outputStream = new FileOutputStream(target);
			byte[] base64Encoded = Base64.getDecoder().decode(file);
			outputStream.write(base64Encoded);
			outputStream.close();
		}
		catch (Exception e) { System.out.println(e); }
		
			return fileName;
		}
		
		
		
		
		
	
	public String generateRandomName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	

}
