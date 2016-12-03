package com.simplenazmul.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.UserPostDao;
import com.simplenazmul.model.LikeUserPost;
import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPost;

@Service("userPostService")
@Transactional
public class UserPostServiceImpl implements UserPostService {

	@Autowired
	private UserPostDao userPostDao;

	@Autowired
	FriendSystemService friendSystemService;

	@Autowired
	UserService userService;

	@Autowired
	LikeUserPostService likeUserPostService;

	@Override
	public void save(UserPost userPost) {
		userPostDao.save(userPost);

	}

	@Override
	public UserPost findById(int id) {
		return userPostDao.findById(id);
	}

	@Override
	public void deleteById(int id) {
		userPostDao.deleteById(id);

	}

	@Override
	public void savePost(UserPost userPost) {
		userPost.getUserPostDiscription();
		userPost.getUser();
		userPost.getPrivacy();

		userPostDao.save(userPost);

	}

	@Override
	public List<UserPost> findAllPost() {
		return userPostDao.findAllPost();
	}

	@Override
	public List<UserPost> findLatest30Post() {
		return userPostDao.findLatest30Post();

	}

	@Override
	public List<UserPost> findPostByUserId(User user) {
		return userPostDao.findPostByUserId(user);
	}

	@Override
	public List<UserPost> findByPrivacy(String privacy) {
		return userPostDao.findByPrivacy(privacy);
	}

	@Override
	public List<UserPost> friendPost(User user) {
		return userPostDao.friendPost(user);
	}

	@Override
	public List<UserPost> publicPost(User user) {
		return userPostDao.publicPost(user);
	}

	@Override
	public List<UserPost> findLoginUserLatestPostByUserId(User user) {
		return userPostDao.findLoginUserLatestPostByUserId(user);
	}

	public String timeAgoFunction(Timestamp time) {

		Timestamp ts = time;
		Date date = new Date();
		date.setTime(ts.getTime());
		// String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);

		try {
			// SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at'
			// HH:mm:ss");

			Date past = date;
			Date now = new Date();
			long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
			long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
			long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
			long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());

			if (seconds <= 10) {
				System.out.println(seconds + " seconds ago");
				return "Few seconds ago";
			} else if (seconds > 10 & seconds < 60) {
				System.out.println(seconds + " seconds ago");
				return seconds + " seconds ago";
			} else if (minutes < 60) {
				System.out.println(minutes + " minutes ago");
				return minutes + " minutes ago";
			} else if (hours < 24) {
				System.out.println(hours + " hours ago");
				return hours + " hours ago";
			} else {
				System.out.println(days + " days ago");
				return days + " days ago";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<UserPost> findNewsFeedPostByUserId(int loginUserId) {

		User loginUser = userService.findByEdnetId(loginUserId);
		List<User> friendList = friendSystemService.findAllFriendListById(loginUserId);

		List<UserPost> userPostList1 = this.findPostByUserId(loginUser);
		
		friendList.forEach(friend->{
			userPostList1.addAll(friendPost(friend));
		});
		

		List<UserPost> uPosts = new ArrayList<UserPost>();

		Collections.sort(userPostList1,
				(o1, o2) -> o2.getUserPostCreatedTime().compareTo(o1.getUserPostCreatedTime()));
		userPostList1.stream().limit(30).forEach(s -> uPosts.add(s));

	//	List<LikeUserPost> ab = new ArrayList<LikeUserPost>();

		try {
			for (UserPost up : uPosts) {
				likeUserPostService.listLikeCountByPostId(up.getUserPostId());
				String time = timeAgoFunction(up.getUserPostCreatedTime());
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

		return uPosts;
	}

	@SuppressWarnings("unused")
	@Override
	public int totalPublicPostByUserId(int userId) {
		User user = userService.findByEdnetId(userId);
		List<UserPost> ls = publicPost(user);
		int count = 0;

		for (UserPost up : ls) {
			count = count + 1;
		}
		return count;
	}

}
