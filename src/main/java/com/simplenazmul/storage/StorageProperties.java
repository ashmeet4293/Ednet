package com.simplenazmul.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {


	// Folder Location For Picture Upload
	private String location = "f:/ednet_folder/ednet_images/";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	


}
