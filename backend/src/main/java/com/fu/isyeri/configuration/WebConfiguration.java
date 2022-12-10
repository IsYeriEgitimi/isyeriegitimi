package com.fu.isyeri.configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfiguration implements WebMvcConfigurer{

	@Autowired
	AppConfiguration appConfiguration;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
			
		registry.addResourceHandler("/protocols/**")
		.addResourceLocations("file:./"+appConfiguration.getProtocolStoragePath()+"/")
		.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
		
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:./"+appConfiguration.getImageStoragePath()+"/")
		.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
		
		
	}
	
	
	
	@Bean
	CommandLineRunner createStorageDirectories() {
		return (args) -> {
			createFolder(appConfiguration.getUploadPath());
			createFolder(appConfiguration.getImageStoragePath());
			createFolder(appConfiguration.getProtocolStoragePath());
		};
	}



	private void createFolder(String path) {
		File folder = new File(path);
		boolean folderExist = folder.exists() && folder.isDirectory();
		if (!folderExist) {
			folder.mkdir();
		}
	}
}
