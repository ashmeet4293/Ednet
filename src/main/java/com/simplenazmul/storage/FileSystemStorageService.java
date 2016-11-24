//package com.simplenazmul.storage;
//
//import java.awt.Image;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//import javax.imageio.ImageIO;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.simplenazmul.controller.ProjectController;
//
//@Service
//public class FileSystemStorageService implements StorageService {
//
////	private final Path rootLocation;
////
////	@Autowired
////	public FileSystemStorageService(StorageProperties properties) {
////		this.rootLocation = Paths.get(properties.getLocation());
////	}
//	
//	Logger logger = LoggerFactory.getLogger(ProjectController.class);
//
//	@Override
//	public void store(Path rootLocation, MultipartFile file) {
//		
//
//	}
//
//	@Override
//	public Stream<Path> loadAll() {
//		try {
//			return Files.walk(rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
//					.map(path -> this.rootLocation.relativize(path));
//		} catch (IOException e) {
//			throw new StorageException("Failed to read stored files", e);
//		}
//
//	}
//
//	@Override
//	public Path load(String filename) {
//		return rootLocation.resolve(filename);
//	}
//
//	@Override
//	public Resource loadAsResource(String filename) {
//
//		try {
//			Path file = load(filename);
//	
//			Resource resource = new UrlResource(file.toUri());
//			if (resource.exists() || resource.isReadable()) {
//				return resource;
//			} else {
//				throw new StorageFileNotFoundException("Could not read file: " + filename);
//
//			}
//		} catch (MalformedURLException e) {
//			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
//		}
//	}
//
//	@Override
//	public void deleteAll() {
//		FileSystemUtils.deleteRecursively(rootLocation.toFile());
//	}
//
//	@Override
//	public void init() {
//		try {
//			Files.createDirectory(rootLocation);
//		} catch (IOException e) {
//			throw new StorageException("Could not initialize storage", e);
//		}
//	}
//}
