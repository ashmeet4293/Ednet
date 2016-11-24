package com.simplenazmul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.simplenazmul.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SimpleNazmulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleNazmulApplication.class, args);
	}

}
