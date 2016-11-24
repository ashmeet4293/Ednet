package com.simplenazmul.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPicAlbum;
import com.simplenazmul.model.UserProfile;
import com.simplenazmul.service.FriendSystemService;
import com.simplenazmul.service.LikeUserPostService;
import com.simplenazmul.service.UserPicAlbumService;
import com.simplenazmul.service.UserPostService;
import com.simplenazmul.service.UserProfileService;
import com.simplenazmul.service.UserService;
import com.simplenazmul.storage.StorageException;
import com.simplenazmul.storage.StorageFileNotFoundException;
import com.simplenazmul.storage.StorageProperties;

@Controller
public class PictureUploadController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserPostService userPostService;

	@Autowired
	FriendSystemService friendSystemService;

	@Autowired
	LikeUserPostService likeUserPostService;

	@Autowired
	UserPicAlbumService userPicAlbumService;

	// @Autowired
	// private StorageService storageService;

	@Autowired
	private StorageProperties storageProperties;

	Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private boolean loginOrNot() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		}
		return false;
	}

	@GetMapping("/users/{userName}/picture_gallery")
	public String userPictureGallery(Model model, @PathVariable("userName") String userName) {
		logger.info("INTO User Picture gallery pageGET");
		User paramUser = userService.findByUserName(userName);
		if (loginOrNot()) {

			// model.addAttribute("files", storageService.loadAll()
			// .map(path -> MvcUriComponentsBuilder
			// .fromMethodName(FileUploadController.class, "serveFile",
			// path.getFileName().toString())
			// .build().toString())
			// .collect(Collectors.toList()));

			model.addAttribute("loginUser", getLoginUser());
			model.addAttribute("paramUser", paramUser);
			model.addAttribute("users", userService.findAllUser());
			return "user_picture_gallery";
		}

		return "redirect:/login";
	}

	// @GetMapping("/users/{userName}/picture_gallery/files/{filename:.+}")
	// @ResponseBody
	// public ResponseEntity<Resource> serveFile(@PathVariable String filename,
	// Model model, @PathVariable("userName") String userName) {
	//
	// Resource file = storageService.loadAsResource(filename);
	// return ResponseEntity.ok()
	// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	// file.getFilename() + "\"")
	// .body(file);
	// }

	@PostMapping("/users/{userName}/picture_gallery")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			Model model, @PathVariable("userName") String userName) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			logger.info("INTO POST Upload");

			User paramUser = userService.findByUserName(userName);
			User loginUser = getLoginUser();

			Path rootLocation = Paths.get(storageProperties.getLocation() + '/' + loginUser.getEdnetUserId());

			// storageService.store(rootLocation, file);

			try {
				if (file.isEmpty()) {
					throw new StorageException("Failed to store empty file ");
				}

				if (file.getSize() >= 1000000) {

					throw new StorageException("File is very Big My ERROR MESSAGE ");

				}

				InputStream input = file.getInputStream();
				logger.info("Picture is " + input);
				try {
					Image image = ImageIO.read(input);
					if (image == null) {

						throw new StorageException("File is not an Image 2 ");
					}

				} catch (Exception e) {

					throw new StorageException("File is not an Image ");

				}

				int loginUserAlbumId = userPicAlbumService.findUserPicAlbumByAlbumCaptionAndUser("All Picture",
						loginUser);
				File theDir;

				if (loginUserAlbumId == 0) {
					try {
						UserPicAlbum createFirstAlbum = new UserPicAlbum();
						createFirstAlbum.setAlbumCaption("All Picture");
						createFirstAlbum.setAlbumCreatedTime(new Timestamp(0));
						createFirstAlbum.setUser(loginUser);

						userPicAlbumService.save(createFirstAlbum);

						Timestamp time;

						// String fileName = loginUser.getEdnetUserId() +
						// time.toString() + ".txt";

						File directory = new File(rootLocation + "/" + loginUser.getEdnetUserId());

						if (!directory.exists()) {
							directory.mkdir();
						}

						theDir = new File(rootLocation + "/" + loginUser.getEdnetUserId());

						// if the directory does not exist, create it
						if (!theDir.exists()) {
							System.out.println("creating directory: " + theDir);
							boolean result = false;

							try {
								theDir.mkdir();
								result = true;
							} catch (SecurityException se) {
								// handle it
							}
							if (result) {
								System.out.println("DIR created");
								logger.info("Dir Created");
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));

			} catch (IOException e) {

				throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
			}

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");

			logger.info("INTO User Picture gallery POST REQUEST");
			model.addAttribute("loginUser", getLoginUser());
			model.addAttribute("paramUser", paramUser);
			model.addAttribute("users", userService.findAllUser());

			return "redirect:/users/{userName}/picture_gallery";
		}

		return "redirect:/login";

	}

	private User getLoginUser() {
		User user = null;
		String email = null;

		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				email = ((UserDetails) principal).getUsername();

			} else {
				email = principal.toString();
			}

			user = userService.findByEmail(email);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@ModelAttribute("roles")
	List<UserProfile> initializeProfiles() {
		System.out.println(userProfileService.findAll());
		return userProfileService.findAll();
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
