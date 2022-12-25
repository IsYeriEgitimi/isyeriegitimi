package com.fu.isyeri.strategies.concretes;


import com.fu.isyeri.configuration.AppConfiguration;
import com.fu.isyeri.strategies.abstracts.FileTypeStrategy;


public class ImageFileTypeStrategy implements FileTypeStrategy{
	
	
	AppConfiguration appConfiguration;
	
	public ImageFileTypeStrategy() {
		this.appConfiguration = new AppConfiguration(); 
	}
	

	@Override
	public String getStoragePath() {
		return appConfiguration.getImageStoragePath();
	}

}
