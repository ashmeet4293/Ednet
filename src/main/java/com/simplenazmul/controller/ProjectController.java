package com.simplenazmul.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simplenazmul.model.FriendSystem;
import com.simplenazmul.model.LikeUserPost;
import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPost;
import com.simplenazmul.model.UserProfile;
import com.simplenazmul.service.FriendSystemService;
import com.simplenazmul.service.LikeUserPostService;
import com.simplenazmul.service.UserPostService;
import com.simplenazmul.service.UserProfileService;
import com.simplenazmul.service.UserService;
import com.simplenazmul.storage.StorageFileNotFoundException;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class ProjectController {

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

	// @Autowired
	// private StorageService storageService;

	Logger logger = LoggerFactory.getLogger(ProjectController.class);

	private boolean loginOrNot() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		}
		return false;
	}

	// @GetMapping("/friend")
	// public String friendTest(ModelMap model) {
	// logger.info("INTO FRIEND TEST");
	//
	// return "friend_test";
	// }

	@GetMapping("/testing")
	public String testing(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			model.addAttribute("username", currentUserName);
			return "test";
		}

		return "redirect:/error";
	}

	@GetMapping("/login")
	public String loginPage(ModelMap model) {
		if (loginOrNot() == true) {
			return "redirect:/";
		}
		return "login";
	}

	@GetMapping("/error")
	public String errorPage(ModelMap model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (!(auth instanceof AnonymousAuthenticationToken)) {
//			logger.info("INTO /ERROR : Login USER");
//
//			User loginUser = getLoginUser();
//
//			logger.info("Login user is : " + loginUser);
//
//			model.addAttribute("loginUser", loginUser);
//			return "error_login_user";
//		}
//		logger.info("INTO /ERROR : anonyomous User");
		return "error";

	}

	@GetMapping(value = "/error/404")
	public String error404(Model model) {
		if (loginOrNot() == true) {

			model.addAttribute("users", userService.findAllUser());
			model.addAttribute("loginUser", getLoginUser());
			model.addAttribute("message", "There is no User for this User Name");
			return "error404";

		}
		model.addAttribute("message", "There is no User for this User Name");

		return "redirect:/error";
	}

	// @PostMapping("/login-process")
	// public String loginProcess(@Valid User user, BindingResult bindingResult,
	// ModelMap model) {
	// logger.info("insert Login service");
	// if (bindingResult.hasErrors()) {
	// // notificationService.addErrorMessage("Please fill the form
	// // Correctly");
	// model.addAttribute("error2", "Fill the form");
	// return "login";
	// }
	// if (userService.isEmailExist(user) == false) {
	// logger.info("email not exist");
	// return "login";
	// }
	// boolean login = userService.userLogin(user.getEmail(),
	// passwordEncoder.encode(user.getPassword()));
	//
	// if(login==true){
	// return "test";
	// }else{
	// return "test2";
	// }
	//
	//// logger.info("insert Login service");
	//// return "redirect:/";
	// }

	@GetMapping("/")
	public String newsfeedPage(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			User loginUser = getLoginUser();
			int loginUserId = loginUser.getEdnetUserId();
			int friendCount = friendSystemService.friendCount(loginUserId);

			List<User> friendList = friendSystemService.findAllFriendListById(loginUserId);
			List<UserPost> uPosts = userPostService.findNewsFeedPostByUserId(loginUserId);
			int friendRequestCount = friendSystemService.totalFriendRequest(loginUserId);
			List<User> friendRequestUser = friendSystemService.findAllFriendRequestUserListById(loginUserId);

			model.addAttribute("friendList", friendList);
			model.addAttribute("friendCount", friendCount);
			model.addAttribute("friendRequestUser", friendRequestUser);
			model.addAttribute("friendRequestCount", friendRequestCount);

			model.addAttribute("loginUser", getLoginUser());
			model.addAttribute("users", userService.findAllUser());
			model.addAttribute("userPost", uPosts);

			return "newsfeed";
		} else {
			return "login";
		}

	}

	@GetMapping(value = "/users/{userName}")
	public String userProfile(@PathVariable("userName") String userName, Model model) {

		boolean userNameExistOrNot = userService.isUserNameExist(userName);

		if (userNameExistOrNot == false) {
			logger.info("There is no User Name like that");
			return "redirect:/error/404";
		}

		User paramUser = userService.findByUserName(userName);
		int paramUserId = paramUser.getEdnetUserId();
		int totalPublicPost = userPostService.totalPublicPostByUserId(paramUserId);
		model.addAttribute("totalPublicPost", totalPublicPost);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			String loginUserName = getLoginUser().getUserName();
			User loginUser = getLoginUser();
			int loginUserId = loginUser.getEdnetUserId();
			int friendRequestCount = friendSystemService.totalFriendRequest(loginUserId);

			model.addAttribute("loginUser", getLoginUser());
			model.addAttribute("friendRequestCount", friendRequestCount);

			// Login User Profile
			if (userName.equals(loginUserName) == true) {
				logger.info("This is Login  user's Page");

				List<UserPost> uPosts = userPostService.findPostByUserId(paramUser);
				List<LikeUserPost> ab = new ArrayList<LikeUserPost>();

				try {
					for (UserPost up : uPosts) {
						ab = likeUserPostService.listLikeCountByPostId(up.getUserPostId());
						// up.setLikeUserPosts(ab);
						String time = userPostService.timeAgoFunction(up.getUserPostCreatedTime());
						logger.info(time);
						up.setTimeAgo(time);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				for (UserPost up1 : uPosts) {
					boolean loginUserlikeValue = likeUserPostService.likeOrNotByPost(up1.getUserPostId(), loginUserId);
					int likeCount = likeUserPostService.likeCountByPost(up1.getUserPostId());
					up1.setLoginUserLikeOrNot(loginUserlikeValue);
					up1.setLikeCount(likeCount);
				}

				int friendCount = friendSystemService.friendCount(loginUserId);
				List<User> friendList = friendSystemService.findAllFriendListById(loginUserId);

				List<User> friendRequestUser = friendSystemService.findAllFriendRequestUserListById(loginUserId);

				model.addAttribute("friendRequestUser", friendRequestUser);
				model.addAttribute("friendCount", friendCount);
				model.addAttribute("friendList", friendList);
				model.addAttribute("users", userService.findAllUser());
				model.addAttribute("userPost", uPosts);

				// Login User Link
				return "profile_login_user";

			} else {

				int friendCount = friendSystemService.friendCount(paramUserId);
				boolean friendOrNot = friendSystemService.friendOrNot(loginUserId, paramUserId);

				model.addAttribute("friendCount", friendCount);
				model.addAttribute("paramUser", userService.findByUserName(userName));

				// if Param User is Friend
				if (friendOrNot == true) {
					List<UserPost> uPosts = userPostService.friendPost(paramUser);
					List<LikeUserPost> likUserPosts1 = new ArrayList<LikeUserPost>();

					try {
						for (UserPost up : uPosts) {
							likUserPosts1 = likeUserPostService.listLikeCountByPostId(up.getUserPostId());
							// up.setLikeUserPosts(likUserPosts1);
							String time = userPostService.timeAgoFunction(up.getUserPostCreatedTime());
							up.setTimeAgo(time);
							boolean loginUserlikeValue = likeUserPostService.likeOrNotByPost(up.getUserPostId(),
									loginUserId);
							int likeCount = likeUserPostService.likeCountByPost(up.getUserPostId());
							up.setLoginUserLikeOrNot(loginUserlikeValue);
							up.setLikeCount(likeCount);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					logger.info("This is FRIEND PROFILE");
					model.addAttribute("userPost", uPosts);
					return "profile_friend";

				} else {
					// if Param User is not Friend
					boolean anyRelation = friendSystemService.haveAnyRelation(loginUserId, paramUserId);
					List<UserPost> uPosts1 = userPostService.publicPost(paramUser);
					List<LikeUserPost> ab1 = new ArrayList<LikeUserPost>();
					try {
						// Binding in Transient fields
						for (UserPost up : uPosts1) {
							ab1 = likeUserPostService.listLikeCountByPostId(up.getUserPostId());
							// up.setLikeUserPosts(ab1);
							String time = userPostService.timeAgoFunction(up.getUserPostCreatedTime());
							up.setTimeAgo(time);
							boolean loginUserlikeValue = likeUserPostService.likeOrNotByPost(up.getUserPostId(),
									loginUserId);
							int likeCount = likeUserPostService.likeCountByPost(up.getUserPostId());
							up.setLoginUserLikeOrNot(loginUserlikeValue);
							up.setLikeCount(likeCount);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (anyRelation == false) {
						model.addAttribute("userPost", uPosts1);
						return "profile_no_relation";

					} else if (anyRelation == true) {

						FriendSystem fs = friendSystemService.findFriendRelation(loginUserId, paramUserId);
						int status = fs.getStatus();
						int lastActionUser = fs.getLastActionUser();

						if (status == 3 & lastActionUser == loginUserId) {
							logger.info("Profile Block by Login User");
							return "profile_block_by_loginuser";

						} else if (status == 3 & lastActionUser == paramUserId) {
							logger.info("Profile Block by Param user");
							return "profile_block_by_paramuser";
						} else {

							model.addAttribute("friendStatus", status);
							model.addAttribute("lastActionUser", lastActionUser);
							model.addAttribute("userPost", uPosts1);

							return "profile_have_any_relation";
						}

					}

				}
			}
		}

		logger.info("This is Guest view  user's Page");

		List<UserPost> uPosts1 = userPostService.publicPost(paramUser);

		List<LikeUserPost> ab1 = new ArrayList<LikeUserPost>();

		try {
			for (UserPost up : uPosts1) {

				ab1 = likeUserPostService.listLikeCountByPostId(up.getUserPostId());

				// up.setLikeUserPosts(ab1);
				String time = userPostService.timeAgoFunction(up.getUserPostCreatedTime());
				logger.info(time);
				up.setTimeAgo(time);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (UserPost up1 : uPosts1) {

			int likeCount = likeUserPostService.likeCountByPost(up1.getUserPostId());
			up1.setLikeCount(likeCount);
		}

		model.addAttribute("users", userService.findAllUser());
		model.addAttribute("paramUser", userService.findByUserName(userName));
		model.addAttribute("userPost", uPosts1);

		return "guest_profile_view";

	}

	@GetMapping("/help")
	public String helpMenu(ModelMap model) {
		return "help_menu";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/";
		} else {
			return "login";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@GetMapping("/user-registration")
	public String registerlink(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			return "redirect:/";
		} else {
			return "user_registration";
		}
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String saveRegistration(@Valid User user, BindingResult result, ModelMap model, Errors error,
			RedirectAttributes redirectAttributes) {

		logger.info("Start Registration Process");

		if ((userService.isEmailExist(user)) && (user.getEmail() != null)) {
			logger.info("Email Checking");
			redirectAttributes.addFlashAttribute("emailExistMessage", "Email is already taken " + "!");
			return "redirect:/user-registration";
		}

		if (userService.isUserExist(user) && (user.getUserName() != null)) {
			redirectAttributes.addFlashAttribute("userExistMessage", "User Name is already taken " + "!");
			return "redirect:/user-registration";
		}

		if (result.hasErrors()) {
			error.getAllErrors();
			redirectAttributes.addFlashAttribute("otherErrorMessage", "Something Error" + "!");
			return "redirect:/user-registration";
		}

		userService.userRegistration(user);

		model.addAttribute("success", "You have been registered successfully");
		return "login";

	}

	@PostMapping(value = "users/posts/create")
	public String createPost(@Valid @RequestBody UserPost userPost, BindingResult result, Model model) {
		logger.info("creating new user: {}");
		System.out.println(userPost);

		User loginUser = getLoginUser();
		userPost.setUser(loginUser);

		if (loginUser.getUserName() == null) {
			logger.info("Login User is Null. Something Fishy");
			// return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		if (userPost.getUserPostDiscription() == "") {
			logger.info("Post Discription is Null");
			// return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		if ((userPost.getPrivacy() == "")) {
			logger.info("Post Privacy is Null");
			// return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		model.addAttribute("newUserPost", userPost);
		userPostService.save(userPost);

		List<UserPost> latestPost = userPostService.findLoginUserLatestPostByUserId(loginUser);

		try {
			for (UserPost up : latestPost) {

				String time = userPostService.timeAgoFunction(up.getUserPostCreatedTime());
				logger.info(time);
				up.setTimeAgo(time);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("userPost", latestPost);
		return "layouts/ajax_post";

	}

	@PostMapping(value = "/like/add")
	public String addLike(@Valid @RequestBody LikeUserPost lup, Model model, BindingResult result) {

		logger.info("INTO Add LIKE Request");

		int pId = lup.getUserPostId();

		if (pId == 0) {
			return "blankpage";
		}

		logger.info("M  " + pId);

		User loginUser = getLoginUser();
		int loginUserId = loginUser.getEdnetUserId();

		lup.setUserPostId(pId);
		lup.setUserId(loginUserId);
		lup.setLikeValue(1);

		logger.info("LIKE TABLE : " + lup);

		likeUserPostService.save(lup);

		int postLikeCount = likeUserPostService.likeCountByPost(pId);

		model.addAttribute("likeCount", postLikeCount);

		return "likecount_page";

	}

	@PostMapping(value = "/like/remove")
	public String unLike(@Valid @RequestBody LikeUserPost lup, Model model) {

		logger.info("INTO Add LIKE Request");

		int pId = lup.getUserPostId();

		if (pId == 0) {
			return "blankpage";
		}

		try {
			User loginUser = getLoginUser();
			int loginUserId = loginUser.getEdnetUserId();

			likeUserPostService.deleteByUserIdAndPostId(loginUserId, pId);

			int postLikeCount = likeUserPostService.likeCountByPost(pId);

			model.addAttribute("likeCount", postLikeCount);

			return "likecount_page";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "blankpage";
		}
	}

	@PostMapping(value = "friend-request/add")
	public ResponseEntity<Void> sendFriendRequest(@Valid @RequestBody FriendSystem friendSystem, BindingResult result,
			Model model) {
		logger.info("INTO FRIEND REQUEST");

		int paramUserId = friendSystem.getUserIdTwo();
		int loginUserId = getLoginUser().getEdnetUserId();

		boolean haveAnyRelation = friendSystemService.haveAnyRelation(loginUserId, paramUserId);

		if (haveAnyRelation == false) {
			friendSystem.setUserIdOne(loginUserId);
			friendSystem.setStatus(0);
			friendSystem.setLastActionUser(loginUserId);

			friendSystemService.saveFriendRelation(friendSystem);

			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			FriendSystem fs = friendSystemService.findFriendRelation(loginUserId, paramUserId);
			if (fs.getStatus() == 2) {
				fs.setUserIdOne(paramUserId);
				fs.setUserIdTwo(loginUserId);
				fs.setStatus(0);
				fs.setLastActionUser(loginUserId);

				friendSystemService.updateFriendRelation(fs);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

	}

	@PostMapping(value = "friend-request/remove")
	public ResponseEntity<Void> cancelFriendRequest(@Valid @RequestBody FriendSystem friendSystem, BindingResult result,
			Model model) {
		int paramUserId = friendSystem.getUserIdTwo();
		int loginUserId = getLoginUser().getEdnetUserId();

		// friendSystem.setUserIdOne(loginUserId);
		// friendSystem.setLastActionUser(loginUserId);

		if (friendSystemService.haveAnyRelation(loginUserId, paramUserId) == true) {

			friendSystemService.deleteFriendRelation(loginUserId, paramUserId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "friend-request/accept")
	public ResponseEntity<Void> acceptFriendRequest(@Valid @RequestBody FriendSystem friendSystem, BindingResult result,
			Model model) {
		int paramUserId = friendSystem.getUserIdTwo();
		int loginUserId = getLoginUser().getEdnetUserId();

		boolean haveAnyRelation = friendSystemService.haveAnyRelation(loginUserId, paramUserId);
		if (haveAnyRelation == true) {
			FriendSystem fs = friendSystemService.findFriendRelation(loginUserId, paramUserId);
			if (fs.getStatus() == 0) {
				fs.setUserIdOne(paramUserId);
				fs.setUserIdTwo(loginUserId);
				fs.setStatus(1);
				fs.setLastActionUser(loginUserId);

				friendSystemService.updateFriendRelation(fs);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "friend-request/reject")
	public ResponseEntity<Void> rejectFriendRequest(@Valid @RequestBody FriendSystem friendSystem, BindingResult result,
			Model model) {
		logger.info("INTO reject request");
		int paramUserId = friendSystem.getUserIdTwo();
		int loginUserId = getLoginUser().getEdnetUserId();

		boolean haveAnyRelation = friendSystemService.haveAnyRelation(loginUserId, paramUserId);
		if (haveAnyRelation == true) {
			FriendSystem fs = friendSystemService.findFriendRelation(loginUserId, paramUserId);
			if (fs.getStatus() == 0) {
				fs.setUserIdOne(paramUserId);
				fs.setUserIdTwo(loginUserId);
				fs.setStatus(2);
				fs.setLastActionUser(loginUserId);

				friendSystemService.updateFriendRelation(fs);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

	}

	@PostMapping(value = "friend-request/block")
	public String blockFriend(@Valid @RequestBody FriendSystem friendSystem, BindingResult result, Model model) {
		logger.info("INTO block request");
		int paramUserId = friendSystem.getUserIdTwo();
		int loginUserId = getLoginUser().getEdnetUserId();

		boolean haveAnyRelation = friendSystemService.haveAnyRelation(loginUserId, paramUserId);

		if (haveAnyRelation == false) {
			friendSystem.setUserIdOne(loginUserId);
			friendSystem.setStatus(3);
			friendSystem.setLastActionUser(loginUserId);

			friendSystemService.saveFriendRelation(friendSystem);

			return "redirect:/";
		} else {
			FriendSystem fs = friendSystemService.findFriendRelation(loginUserId, paramUserId);

			fs.setUserIdOne(paramUserId);
			fs.setUserIdTwo(loginUserId);
			fs.setStatus(3);
			fs.setLastActionUser(loginUserId);

			friendSystemService.updateFriendRelation(fs);
			return "redirect:/";

		}

	}

	@PostMapping(value = "friend-request/unblock")
	public String unblockUser(@Valid @RequestBody FriendSystem friendSystem, Model model) {
		int paramUserId = friendSystem.getUserIdTwo();
		int loginUserId = getLoginUser().getEdnetUserId();

		boolean haveAnyRelation = friendSystemService.haveAnyRelation(loginUserId, paramUserId);

		if (haveAnyRelation == true) {

			friendSystemService.deleteFriendRelation(loginUserId, paramUserId);

			return "redirect:/users/{userName}";
		} else {

			return "redirect:/";

		}
	}

	@GetMapping(value = "/user-friend-requests")
	public String userFriendRequests(Model model) {

		if (loginOrNot() == true) {
			User user = getLoginUser();
			int loginUserId = user.getEdnetUserId();
			List<FriendSystem> fs = friendSystemService.findAllFriendRequests(loginUserId);
			logger.info("INTO ALL FRIEND REQUESTS");
			System.out.println(fs);

			List<User> friendRequestSentUser = new ArrayList();

			for (FriendSystem fsSender : fs) {
				if (fsSender.getUserIdOne() == loginUserId) {
					int userId = fsSender.getUserIdTwo();
					User requestSender = userService.findByEdnetId(userId);
					friendRequestSentUser.add(requestSender);
				} else {
					int userId = fsSender.getUserIdOne();
					User requestSender = userService.findByEdnetId(userId);
					friendRequestSentUser.add(requestSender);
				}
			}
			
			int friendRequestCount = friendSystemService.totalFriendRequest(loginUserId);
			
			model.addAttribute("friendRequestCount", friendRequestCount);
			model.addAttribute("requestSender", friendRequestSentUser);
			model.addAttribute("loginUser", getLoginUser());
			model.addAttribute("loginUserId", loginUserId);
			return "user_friend_requests";
		}

		return "redirect:/login";

	}

	@PostMapping(value = "/search")
	public String navbarSearch(@RequestBody String searchTerm, BindingResult result, Model model) {
		logger.info(searchTerm);

		List<User> userList = userService.findAllUsersBySearchTerm(searchTerm);

		model.addAttribute("searchUserList", userList);
		return "navbar_search_page";

	}

	@GetMapping(value = "/users/{userName}/edit-user-profile")
	public String editUserProfile(Model model) {

		model.addAttribute("loginUser", getLoginUser());
		return "edit_user_profile";
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

	@ModelAttribute("roles")
	List<UserProfile> initializeProfiles() {
		System.out.println(userProfileService.findAll());
		return userProfileService.findAll();
	}

}
