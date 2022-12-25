package com.fu.isyeri.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "isyeri")
public class AppConfiguration {

	private String uploadPath = "file-storage";
	
	private String protocolStorage = "protocol-storage";
	
	private String imageStorage = "image-storage";
	
	public String getProtocolStoragePath() {
		return uploadPath + "/"+ protocolStorage;
	}
	
	public String getImageStoragePath() {
		return uploadPath + "/"+ imageStorage;
	}
}
