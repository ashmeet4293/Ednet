package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.LikeUserPic;

public interface LikeUserPicDao {
	
	void save(LikeUserPic likeUserPic);

	void updateUserPicLike(LikeUserPic likeUserPic);

	void deleteByLikeId(int likeId);

	void deleteByUserIdAndPicId(int userId, int userPicId);

	List<LikeUserPic> listLikeCountByPicId(int userPicId);

	boolean likeOrNotByPic(int userPicId, int userId);

	int likeCountByPic(int userPicId);
}
