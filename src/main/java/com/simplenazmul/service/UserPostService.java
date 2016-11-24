package com.simplenazmul.service;

import java.sql.Timestamp;
import java.util.List;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPost;

public interface UserPostService {

	void save(UserPost userPost);

	UserPost findById(int id);

	void deleteById(int id);

	void savePost(UserPost userPost);

	List<UserPost> findAllPost();

	List<UserPost> findLatest30Post();

	List<UserPost> findPostByUserId(User user);

	List<UserPost> findByPrivacy(String privacy);

	List<UserPost> findNewsFeedPostByUserId(int loginUserId);

	List<UserPost> friendPost(User user);

	List<UserPost> publicPost(User user);

	List<UserPost> findLoginUserLatestPostByUserId(User user);

	String timeAgoFunction(Timestamp time);

	int totalPublicPostByUserId(int userId);

}
