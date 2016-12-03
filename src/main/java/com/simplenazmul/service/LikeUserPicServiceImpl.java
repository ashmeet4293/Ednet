package com.simplenazmul.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simplenazmul.dao.LikeUserPicDao;
import com.simplenazmul.model.LikeUserPic;

@Service
public class LikeUserPicServiceImpl implements LikeUserPicService {

	LikeUserPicDao dao;

	@Override
	public void save(LikeUserPic likeUserPic) {
		dao.save(likeUserPic);
		
	}

	@Override
	public void updateUserPicLike(LikeUserPic likeUserPic) {
		dao.updateUserPicLike(likeUserPic);
		
	}

	@Override
	public void deleteByLikeId(int likeId) {
		dao.deleteByLikeId(likeId);
		
	}

	@Override
	public void deleteByUserIdAndPicId(int userId, int userPicId) {
		dao.deleteByUserIdAndPicId(userId, userPicId);
		
	}

	@Override
	public List<LikeUserPic> listLikeCountByPicId(int userPicId) {
		return dao.listLikeCountByPicId(userPicId);
	}

	@Override
	public boolean likeOrNotByPic(int userPicId, int userId) {
		return dao.likeOrNotByPic(userPicId, userId);
	}

	@Override
	public int likeCountByPic(int userPicId) {
		return dao.likeCountByPic(userPicId);
	}
}
