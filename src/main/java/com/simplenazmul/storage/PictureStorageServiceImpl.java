package com.simplenazmul.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.simplenazmul.controller.ProjectController;

@Service
public class PictureStorageServiceImpl implements PictureStorageService {

	private final Path rootLocation;

	@Autowired
	public PictureStorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	Logger logger = LoggerFactory.getLogger(ProjectController.class);


	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {

		try {
			Path file = load(filename);
	
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public String imageName(int loginUserId) {
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month= now.get(Calendar.DAY_OF_MONTH);
		int date = now.getMaximum(Calendar.DATE);
		String yearInString = String.valueOf(year);
		String monthInString = String.valueOf(month);
		String dateInString  = String.valueOf(date);

		Timestamp cTime = new Timestamp(System.currentTimeMillis());
		String imageName = "upic"+loginUserId+yearInString+monthInString+dateInString+cTime.hashCode()+".jpg";
		
		return imageName;
	}

	@Override
	public String totalSpace(Path rootLocation) {
		
		
	    long size = 0;
		try {
			size = Files.walk(rootLocation)
			                 .filter(p -> p.toFile().isFile())
			                 .mapToLong(p -> p.toFile().length())
			                 .sum();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    
		 String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		    int unitIndex = (int) (Math.log10(size) / 3);
		    double unitValue = 1 << (unitIndex * 10);
		 
		    String readableSize = new DecimalFormat("#,##0.#")
		                                .format(size / unitValue) + " "
		                                + units[unitIndex];
		    
		    
		    return readableSize;
		
		
		
	}

	
}
