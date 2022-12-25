package com.fu.isyeri.strategies.concretes;

import com.fu.isyeri.configuration.AppConfiguration;
import com.fu.isyeri.strategies.abstracts.FileTypeStrategy;


public class ProtocolFileTypeStrategy implements FileTypeStrategy{
	
	AppConfiguration appConfiguration;
	
	public ProtocolFileTypeStrategy() {
		this.appConfiguration = new AppConfiguration(); 
	}
	
	
	@Override
	public String getStoragePath() {
		return appConfiguration.getProtocolStoragePath();
	}

}
