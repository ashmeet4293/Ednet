package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.LikeUserPost;

public interface LikeUserPostDao {

	void save(LikeUserPost likeUserPost);

	void updateUserPostLike(LikeUserPost likeUserPost);

	void deleteByLikeId(int likeId);

	void deleteByUserIdAndPostId(int userId, int userPostId);

	List<LikeUserPost> listLikeCountByPostId(int userPostId);

	boolean likeOrNotByPost(int userPostId, int userId);

	int likeCountByPost(int userPostId);

}
