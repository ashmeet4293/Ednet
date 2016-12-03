package com.simplenazmul.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPic;
import com.simplenazmul.model.UserPicAlbum;
import com.simplenazmul.service.FriendSystemService;
import com.simplenazmul.service.LikeUserPostService;
import com.simplenazmul.service.UserPicAlbumService;
import com.simplenazmul.service.UserPicService;
import com.simplenazmul.service.UserPostService;
import com.simplenazmul.service.UserProfileService;
import com.simplenazmul.service.UserService;
import com.simplenazmul.storage.PictureStorageService;
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

	@Autowired
	UserPicService userPicService;

	@Autowired
	PictureStorageService pictureStorageService;

	private final Path rootLocation;

	@Autowired
	public PictureUploadController(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

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
		int paramUserId = paramUser.getEdnetUserId();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			// int friendRequestCount =
			// friendSystemService.totalFriendRequest(loginUserId);

			int loginUserId = getLoginUser().getEdnetUserId();
			String paramUserName = userName;
			String loginUserName = getLoginUser().getUserName();

			if (paramUserName.equals(loginUserName) == true) {
				logger.info("This is Login User Picture Gallery");

				List<UserPic> picList = userPicService.findRecent20PicByLoginUserId(loginUserId);

				for (UserPic upic : picList) {
					String time = userPicService.timeAgoFunction(upic.getPictureCreatedTimestamp());
					upic.setTimeAgo(time);
				}

				Path rootLocation2 = null;
				String directory = rootLocation.toString() + "\\" + paramUserId + "\\original_size_pic";
				String totalSize = null;

				try {
					rootLocation2 = Paths.get(directory);
					logger.info("PATH :" + rootLocation2);
					totalSize = pictureStorageService.totalSpace(rootLocation2);
				} catch (Exception e) {
					totalSize = "0 MB";
					e.printStackTrace();

				}

				model.addAttribute("totalSize", totalSize);
				// model.addAttribute("friendRequestCount", friendRequestCount);
				model.addAttribute("loginUser", getLoginUser());
				model.addAttribute("paramUser", paramUser);
				model.addAttribute("picList", picList);

				return "user_picture_gallery_login_user";
			} else {
				boolean friendOrNot = friendSystemService.friendOrNot(loginUserId, paramUserId);

				if (friendOrNot == true) {
					logger.info("Param User is Friend");

					List<UserPic> picList = userPicService.findRecent20PicByFriendUserId(paramUserId);

					for (UserPic upic : picList) {
						String time = userPicService.timeAgoFunction(upic.getPictureCreatedTimestamp());
						upic.setTimeAgo(time);
					}

					Path rootLocation2 = null;
					String directory = rootLocation.toString() + "\\" + paramUserId + "\\original_size_pic";
					String totalSize = null;

					try {
						rootLocation2 = Paths.get(directory);
						logger.info("PATH :" + rootLocation2);
						totalSize = pictureStorageService.totalSpace(rootLocation2);
					} catch (Exception e) {
						totalSize = "0 MB";
						e.printStackTrace();

					}

					model.addAttribute("totalSize", totalSize);
					// model.addAttribute("friendRequestCount",
					// friendRequestCount);
					model.addAttribute("loginUser", getLoginUser());
					model.addAttribute("paramUser", paramUser);
					model.addAttribute("picList", picList);

					return "user_picture_gallery_friend_user";
				} else {
					logger.info("Param User is not friend");

					List<UserPic> picList = userPicService.findRecent20PicByPublicUserId(paramUserId);

					for (UserPic upic : picList) {
						String time = userPicService.timeAgoFunction(upic.getPictureCreatedTimestamp());
						upic.setTimeAgo(time);
					}

					Path rootLocation2 = null;
					String directory = rootLocation.toString() + "\\" + paramUserId + "\\original_size_pic";
					String totalSize = null;

					try {
						rootLocation2 = Paths.get(directory);
						logger.info("PATH :" + rootLocation2);
						totalSize = pictureStorageService.totalSpace(rootLocation2);
					} catch (Exception e) {
						totalSize = "0 MB";
						e.printStackTrace();

					}

					model.addAttribute("totalSize", totalSize);
					// model.addAttribute("friendRequestCount",
					// friendRequestCount);
					model.addAttribute("loginUser", getLoginUser());
					model.addAttribute("paramUser", paramUser);
					model.addAttribute("picList", picList);

					return "user_picture_gallery_public_user";
				}
			}
		
		}
		return "redirect:/login";

	}

	@PostMapping("profile-picture/add")
	public ResponseEntity<Void> makeProfilePicture(@RequestBody UserPic upic, Model model) {

		logger.info("Profile Picture Making");
		if (loginOrNot()) {
			User loginUser = getLoginUser();
			int loginUserId = loginUser.getEdnetUserId();
			String generatedPictureName = upic.getPictureNameAutoGenerated();
			logger.info("PictureLink :" + generatedPictureName);

			String sourceLink = rootLocation.toString() + "\\" + loginUserId + "\\original_size_pic\\"
					+ generatedPictureName;
			String destLink = rootLocation.toString() + "\\" + loginUserId + "\\profile_size_pic\\";
			String existsCheck = rootLocation.toString() + "\\" + loginUserId + "\\profile_size_pic\\"
					+ generatedPictureName;

			File source = new File(sourceLink);
			File existsOrNot = new File(existsCheck);

			boolean pictureExists = existsOrNot.exists();

			if (pictureExists == false) {
				String profilePicCreatePath = Paths.get(destLink, generatedPictureName).toString();

				BufferedImage image = null;
				try {
					image = ImageIO.read(source);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					// Saving profile Pic Images
					BufferedImage profileImage = null;
					try {
						profileImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 120, 120);
						if (profileImage.getWidth() > 120) {
							profileImage = Scalr.crop(profileImage, (profileImage.getWidth() - 120) / 2, 0, 120, 120);

						} else if (profileImage.getHeight() > 120) {
							profileImage = Scalr.crop(profileImage, 0, (profileImage.getHeight() - 120) / 2, 120, 120);

						}

					} catch (IllegalArgumentException | ImagingOpException e) {
						System.out.println("imgscalr threw an exception: " + e.getMessage());
					}

					logger.info("profile Pic size image Resize complete");
					ImageIO.write(profileImage, "jpg", new File(profilePicCreatePath));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			loginUser.setProfilePicLink(generatedPictureName);
			userService.updateUser(loginUser);

			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/users/{userName}/picture/{pictureNameAutoGenerated}")
	public String displaySinglePicture(@PathVariable String userName, @PathVariable String pictureNameAutoGenerated,
			Model model) {
		
		User paramUser = userService.findByUserName(userName);
		String picAutoGeneratedName = pictureNameAutoGenerated + ".jpg";
		UserPic userPic = userPicService.findByAutoGeneratedPicName(picAutoGeneratedName);
		
		String time = userPicService.timeAgoFunction(userPic.getPictureCreatedTimestamp());
		userPic.setTimeAgo(time);
		
		int pictureOwnerId = userPic.getUserId();
		User pictureOwner = userService.findByEdnetId(pictureOwnerId);
		String picturePrivacy = userPic.getPrivacy();

		if (loginOrNot()) {
			User loginUser = getLoginUser();
			
			int loginUserId = getLoginUser().getEdnetUserId();
			int paramUserId = paramUser.getEdnetUserId();
			String paramUserName = userName;
			String loginUserName = getLoginUser().getUserName();
			

			model.addAttribute("loginUser", loginUser);
			
			if(loginUserId == pictureOwnerId){
				logger.info("picture view by its owner");
				
				
				model.addAttribute("userPic", userPic);
				model.addAttribute("pictureOwner", pictureOwner);
				model.addAttribute("paramUser", paramUser);
				return "single_picture_owner";
			}else{
				boolean friendOrNot  = friendSystemService.friendOrNot(loginUserId, pictureOwnerId);
				
				if (friendOrNot == true) {
					if((picturePrivacy.equals("Public")) || (picturePrivacy.equals("Friends"))){
						
						model.addAttribute("userPic", userPic);
						model.addAttribute("pictureOwner", pictureOwner);
						model.addAttribute("paramUser", paramUser);
						return "single_picture_friend";
					}else{
						
						return "single_picture_no_rights_view";
					}
					
					
				}else{
					if(picturePrivacy.equals("Public")){
						
						model.addAttribute("userPic", userPic);
						model.addAttribute("pictureOwner", pictureOwner);
						model.addAttribute("paramUser", paramUser);
						return "single_picture_public";
					}else{
						return "single_picture_no_rights_view";
					}
					
				}
			}

		}
		
		if(picturePrivacy.equals("Public")){
			
			model.addAttribute("userPic", userPic);
			model.addAttribute("pictureOwner", pictureOwner);
			model.addAttribute("paramUser", paramUser);
			return "single_picture_guest_view";
		
		}else{
			
			return "single_picture_guest_no_rights_view";
		}

		
	}

	@GetMapping("/ednet_images/{userId}/profile_size_pic/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile2(@PathVariable String filename, @PathVariable int userId) {

		String directory = rootLocation.toString() + "\\" + userId + "\\profile_size_pic\\";
		Path rootLocation2 = Paths.get(directory);
		logger.info("RootLocation is :: " + rootLocation2);

		Resource file;

		try {
			Path path = rootLocation2.resolve(filename);

			Resource resource = new UrlResource(path.toUri());
			if (resource.exists() || resource.isReadable()) {
				file = resource;

			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/ednet_images/{userId}/medium_size_pic/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile3(@PathVariable String filename, @PathVariable int userId) {

		String directory = rootLocation.toString() + "\\" + userId + "\\medium_size_pic";
		Path rootLocation2 = Paths.get(directory);
		logger.info("RootLocation is :: " + rootLocation2);

		Resource file;

		try {
			Path path = rootLocation2.resolve(filename);

			Resource resource = new UrlResource(path.toUri());
			if (resource.exists() || resource.isReadable()) {
				file = resource;

			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/ednet_images/{userId}/gallery_size_pic/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename, @PathVariable int userId) {

		String directory = rootLocation.toString() + "\\" + userId + "\\gallery_size_pic";
		Path rootLocation2 = Paths.get(directory);
		logger.info("RootLocation is :: " + rootLocation2);

		Resource file;

		try {
			Path path = rootLocation2.resolve(filename);

			Resource resource = new UrlResource(path.toUri());
			if (resource.exists() || resource.isReadable()) {
				file = resource;

			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@PostMapping("/users/{userName}/picture/{pictureId}/delete")
	public String deleteSinglePicture( @PathVariable("pictureId") String pictureId, Model model, RedirectAttributes redirectAttributes ){
		
		if(loginOrNot()==true){
			int pictureId2 = Integer.parseInt(pictureId);
			int loginUserId = getLoginUser().getEdnetUserId();
			User loginUser = getLoginUser();
			
			UserPic userPic = userPicService.findById(pictureId2);
			String pictureNameAutoGenerated= userPic.getPictureNameAutoGenerated();
			
			String userMainDirectory = rootLocation.toString() + "\\" + loginUserId;
			String userProfilePicDirectory = userMainDirectory + "\\profile_size_pic";
			String mediumSizeDirectory = userMainDirectory + "\\medium_size_pic";
			String originalSizeDirectory = userMainDirectory + "\\original_size_pic";
			String gallerySizeDirectory = userMainDirectory + "\\gallery_size_pic";
			
			File profilePicture = new File(userProfilePicDirectory,pictureNameAutoGenerated );
			File mediumPicture = new File(mediumSizeDirectory,pictureNameAutoGenerated );
			File galleryPicture = new File(gallerySizeDirectory,pictureNameAutoGenerated );
			File originalPicture = new File(originalSizeDirectory,pictureNameAutoGenerated );
			
			boolean a = true,b,c,d;
			if(profilePicture.exists()){
				a = profilePicture.delete();
				a = true;
			}
			
		
			b = galleryPicture.delete();
			c = mediumPicture.delete();
			d = originalPicture.delete();
			
			if((a==true)& (b==true) & (c==true) & (d==true)){
				
				String currentProfilePicture = loginUser.getProfilePicLink();
				logger.info(currentProfilePicture);
				logger.info(pictureNameAutoGenerated);
				if(currentProfilePicture.equals(pictureNameAutoGenerated)){
					logger.info("into LOOP");
					loginUser.setProfilePicLink("");
					userService.updateUser(loginUser);
				}
				
				userPicService.deleteById(userPic.getPictureId());
				logger.info("Deleted this picture");
				
				redirectAttributes.addFlashAttribute("message", "You deleted your picture " + pictureNameAutoGenerated + "!");
				return "redirect:/users/{userName}/picture_gallery/";
			}else{
				logger.info("something problem about delete");
				redirectAttributes.addFlashAttribute("message", "You deleted your picture " + pictureNameAutoGenerated + "!");
				return "redirect:/";
			}
			
			
			
			
			
		}
		
		return "redirect:/login";
	}

	@PostMapping("/users/{userName}/picture/{pictureId}/update")
	public String userPictureUpdate(@RequestParam("pictureCaption") String pictureCaption,
			@RequestParam("privacy") String privacy, @PathVariable("pictureId") String pictureId, Model model,
			@PathVariable("userName") String userName) {
		logger.info("INTO PICTURE INFO UPDATE");
		int pictureId2 = Integer.parseInt(pictureId);
		
		UserPic userPic = userPicService.findById(pictureId2);
		try {
			userPic.setPictureCaption(pictureCaption);
			userPic.setPrivacy(privacy);
			userPicService.updatePic(userPic);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/users/{userName}/picture_gallery";
	}

	@PostMapping("/users/{userName}/picture_gallery")
	public String userPictureUpload(@RequestParam("file") MultipartFile file, String pictureCaption, String privacy,
			RedirectAttributes redirectAttributes, Model model, @PathVariable("userName") String userName,
			HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			logger.info("INTO POST Upload");


			User paramUser = userService.findByUserName(userName);
			User loginUser = getLoginUser();
			int loginUserId = loginUser.getEdnetUserId();

			// User Picture Album check and if not available then create one
			int loginUserAlbumId = userPicAlbumService.findUserPicAlbumByAlbumCaptionAndUser("All Picture", loginUser);
			logger.info("Album Id :" + loginUserAlbumId);
			if (loginUserAlbumId == 0) {
				try {
					UserPicAlbum createFirstAlbum = new UserPicAlbum();
					createFirstAlbum.setAlbumCaption("All Picture");
					createFirstAlbum.setAlbumCreatedTime(new Timestamp(System.currentTimeMillis()));
					createFirstAlbum.setUser(loginUser);

					userPicAlbumService.save(createFirstAlbum);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			try {
				if (file.isEmpty()) {
					throw new StorageException("Failed to store empty file ");
				}

				if (file.getSize() >= 1000000) {
					throw new StorageException("File is very Big ");
				}

				InputStream input = file.getInputStream();
				try {
					Image image = ImageIO.read(input);
					if (image == null) {
						throw new StorageException("File is not an Image 2 ");
					}

				} catch (Exception e) {
					throw new StorageException("File is not an Image ");
				}
				
				
				InputStream imageTest1 = file.getInputStream();
				BufferedImage imageTest2 = ImageIO.read(imageTest1);
				
				if(imageTest2.getHeight() <120 || imageTest2.getWidth()<120){
					throw new StorageException("Image is too small for upload. You should upload at least 120x120 px . ");
				}

				loginUserAlbumId = userPicAlbumService.findUserPicAlbumByAlbumCaptionAndUser("All Picture", loginUser);
				String imageName = pictureStorageService.imageName(loginUserId);

				String userMainDirectory = rootLocation.toString() + "\\" + loginUserId;
				String userProfilePicDirectory = userMainDirectory + "\\profile_size_pic";
				String mediumSizeDirectory = userMainDirectory + "\\medium_size_pic";
				String originalSizeDirectory = userMainDirectory + "\\original_size_pic";
				String gallerySizeDirectory = userMainDirectory + "\\gallery_size_pic";

				File loginUserMainDirectory = new File(userMainDirectory);
				boolean directoryExists = loginUserMainDirectory.exists();
				logger.info("Login User Directory Available ?  " + directoryExists);

				if (directoryExists == false) {
					try {
						File mainDirectory = new File(userMainDirectory);
						File profilePicDirectory = new File(userProfilePicDirectory);
						File mediumPicDirectory = new File(mediumSizeDirectory);
						File originalPicDirectory = new File(originalSizeDirectory);
						File gallerPicDirectory = new File(gallerySizeDirectory);
						mainDirectory.mkdir();
						profilePicDirectory.mkdir();
						mediumPicDirectory.mkdir();
						originalPicDirectory.mkdir();
						gallerPicDirectory.mkdir();

						logger.info("Directory Created ");

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				String originalPicCreatePath = Paths.get(originalSizeDirectory, imageName).toString();
				String profilePicCreatePath = Paths.get(userProfilePicDirectory, imageName).toString();
				String mediumPicCreatePath = Paths.get(mediumSizeDirectory, imageName).toString();
				String galleryPicCreatePath = Paths.get(gallerySizeDirectory, imageName).toString();

				
				
				// Saving Images

				try {

					InputStream inputImage = file.getInputStream();
					BufferedImage image = ImageIO.read(inputImage);

					if (image.getHeight() > 800 || image.getWidth() > 800) {

						try {
							// Saving Medium Pic Images
							BufferedImage mediumImage = null;
							try {
								mediumImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 800,
										800);
								if (mediumImage.getWidth() > 800) {
									mediumImage = Scalr.crop(mediumImage, (mediumImage.getWidth() - 800) / 2, 0, 800,
											800);
								} else if (mediumImage.getHeight() > 800) {
									mediumImage = Scalr.crop(mediumImage, 0, (mediumImage.getHeight() - 800) / 2, 800,
											800);
								}
							} catch (IllegalArgumentException | ImagingOpException e) {
								System.out.println("imgscalr threw an exception: " + e.getMessage());
							}

							ImageIO.write(mediumImage, "jpg", new File(mediumPicCreatePath));
							logger.info("Medium size image Resize complete");

						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {

						try {

							ImageIO.write(image, "jpg", new File(mediumPicCreatePath));
							logger.info("Medium size image save without resizing");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						// Saving Gallery Pic Images
						BufferedImage galleryImage = null;
						try {
							galleryImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 120,
									120);
							if (galleryImage.getWidth() > 120) {
								galleryImage = Scalr.crop(galleryImage, (galleryImage.getWidth() - 120) / 2, 0, 120,
										120);
							} else if (galleryImage.getHeight() > 120) {
								galleryImage = Scalr.crop(galleryImage, 0, (galleryImage.getHeight() - 120) / 2, 120,
										120);
							}
						} catch (IllegalArgumentException | ImagingOpException e) {
							System.out.println("imgscalr threw an exception: " + e.getMessage());
						}

						logger.info("Gallery Pic size image Resize complete");
						ImageIO.write(galleryImage, "jpg", new File(galleryPicCreatePath));

					} catch (Exception e) {
						e.printStackTrace();
					}

					try {

						ImageIO.write(image, "jpg", new File(originalPicCreatePath));
						logger.info("Original Image Save complete");

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				UserPic userPic = new UserPic();
				logger.info("Before Database save");

				try {

					if (pictureCaption != null) {
						userPic.setPictureCaption(pictureCaption);
					}
					userPic.setAlbum(loginUserAlbumId);
					userPic.setPrivacy(privacy);
					userPic.setUserId(loginUserId);
					userPic.setPictureNameAutoGenerated(imageName);

					userPicService.save(userPic);
					logger.info("Database PIC Uploaded");

					redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + imageName + "!");

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (IOException e) {

				throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
			}

			logger.info("INTO User Picture gallery POST REQUEST");
			model.addAttribute("loginUser", loginUser);
			model.addAttribute("paramUser", paramUser);

			return "redirect:/users/{userName}/picture_gallery";
		}

		return "redirect:/login";

	}

	private User getLoginUser() {
		User user = null;
		String currentEmail = null;

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			currentEmail = auth.getName();
			user = userService.findByEmail(currentEmail);

			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Login User Null");
		return null;
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

	private BufferedImage cropImageSquare(byte[] image) throws IOException {
		// Get a BufferedImage object from a byte array
		InputStream in = new ByteArrayInputStream(image);
		BufferedImage originalImage = ImageIO.read(in);

		// Get image dimensions
		int height = originalImage.getHeight();
		int width = originalImage.getWidth();

		// The image is already a square
		if (height == width) {
			return originalImage;
		}

		// Compute the size of the square
		int squareSize = (height > width ? width : height);

		// Coordinates of the image's middle
		int xc = width / 2;
		int yc = height / 2;

		// Crop
		BufferedImage croppedImage = originalImage.getSubimage(xc - (squareSize / 2), // x
																						// coordinate
																						// of
																						// the
																						// upper-left
																						// corner
				yc - (squareSize / 2), // y coordinate of the upper-left corner
				squareSize, // widht
				squareSize // height
		);

		return croppedImage;
	}

}
