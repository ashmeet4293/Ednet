package com.simplenazmul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.LikeUserPostDao;
import com.simplenazmul.model.LikeUserPost;

@Service
@Transactional
public class LikeUserPostServiceImpl implements LikeUserPostService {

	@Autowired
	LikeUserPostDao dao;

	@Override
	public void save(LikeUserPost likeUserPost) {
		dao.save(likeUserPost);

	}

	@Override
	public void updateUserPostLike(LikeUserPost likeUserPost) {
		dao.updateUserPostLike(likeUserPost);

	}

	@Override
	public void deleteByLikeId(int likeId) {
		dao.deleteByLikeId(likeId);

	}

	@Override
	public List<LikeUserPost> listLikeCountByPostId(int userPostId) {
		return dao.listLikeCountByPostId(userPostId);
	}

	@Override
	public boolean likeOrNotByPost(int userPostId, int userId) {
		return dao.likeOrNotByPost(userPostId, userId);
	}

	@Override
	public int likeCountByPost(int userPostId) {
		return dao.likeCountByPost(userPostId);
	}

	@Override
	public void deleteByUserIdAndPostId(int userId, int userPostId) {
		dao.deleteByUserIdAndPostId(userId, userPostId);

	}

}
