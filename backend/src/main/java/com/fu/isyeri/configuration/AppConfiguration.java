package com.fu.isyeri.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "isyeri")
public class AppConfiguration {

	private String uploadPath;
	
	private String protocolStorage;
	
	public String getProtocolStoragePath() {
		return uploadPath + "/"+ protocolStorage;
	}
}
