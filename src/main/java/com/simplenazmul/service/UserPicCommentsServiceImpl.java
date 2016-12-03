package com.simplenazmul.service;

import org.springframework.stereotype.Service;

import com.simplenazmul.dao.UserPicCommentsDao;
import com.simplenazmul.model.UserPicComments;

@Service
public class UserPicCommentsServiceImpl implements UserPicCommentsService {

	UserPicCommentsDao dao;
	
	@Override
	public void save(UserPicComments userPicComments) {
		dao.save(userPicComments);

	}

	@Override
	public void updateUserPicComments(UserPicComments userPicComments) {
		dao.updateUserPicComments(userPicComments);

	}

	@Override
	public void delete(int upicCommentId) {
		dao.delete(upicCommentId);

	}

}
