package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPost;

public interface UserPostDao {

	void save(UserPost userPost);

	UserPost findById(int id);

	void deleteById(int id);

	List<UserPost> findAllPost();

	List<UserPost> findLatest30Post();

	List<UserPost> findPostByUserId(User user);

	List<UserPost> findByPrivacy(String privacy);


	List<UserPost> friendPost(User user);

	List<UserPost> publicPost(User user);

	List<UserPost> orderUserPost(List<UserPost> userPosts);

	List<UserPost> findLoginUserLatestPostByUserId(User user);

}
