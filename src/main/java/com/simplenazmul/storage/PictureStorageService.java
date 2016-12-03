package com.simplenazmul.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

public interface PictureStorageService {
	
		Stream<Path> loadAll();

	    Path load(String filename);

	    Resource loadAsResource(String filename);
	    
	    String imageName(int loginUserId);
	    
	    String totalSpace(Path rootLocation);
	    
	    
}
