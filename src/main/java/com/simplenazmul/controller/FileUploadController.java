//package com.simplenazmul.controller;
//
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.simplenazmul.service.UserService;
//import com.simplenazmul.storage.StorageFileNotFoundException;
//import com.simplenazmul.storage.StorageService;
//
//@Controller
//public class FileUploadController {
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	private StorageService storageService;
//
//	Logger logger = LoggerFactory.getLogger(ProjectController.class);
//
//	// private final Path rootLocation = Paths.get("upload-dir/image-dir/");
//
//	// @Autowired
//	// UserPicService userPicService;
//	//
//	// @Autowired
//	// UserService userService;
//	//
//	// @Autowired
//	// UserPicAlbumService userPicAlbumService;
//	//
//	// @Autowired
//	// PrivacyService privacyService;
//	
//	
//	
//		
//	
//
//	@GetMapping("/upload")
//	public String listUploadedFiles(Model model) throws IOException {
//		logger.info("INTO GET Upload");
//
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (!(auth instanceof AnonymousAuthenticationToken)) {
//
//			model.addAttribute("files", storageService.loadAll()
//					.map(path -> MvcUriComponentsBuilder
//							.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
//							.build().toString())
//					.collect(Collectors.toList()));
//			return "uploadForm";
//		}
//
//		return "redirect:/login";
//
//	}
//
//	@GetMapping("upload/files/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//		Resource file = storageService.loadAsResource(filename);
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//				.body(file);
//	}
//
//	@PostMapping("/upload")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//		
//
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (!(auth instanceof AnonymousAuthenticationToken)) {
//			logger.info("INTO POST Upload");
//			
//			
//			
//			
//			storageService.store(file);
//
//			redirectAttributes.addFlashAttribute("message",
//					"You successfully uploaded " + file.getOriginalFilename() + "!");
//
//			return "redirect:/upload";
//		}
//
//		return "redirect:/login";
//
//	}
//
//	@ExceptionHandler(StorageFileNotFoundException.class)
//	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
//		return ResponseEntity.notFound().build();
//	}
//
//}
