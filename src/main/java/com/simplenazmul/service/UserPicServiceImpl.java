package com.simplenazmul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.UserPicDao;
import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPic;
import com.simplenazmul.model.UserPicAlbum;

@Service
@Transactional
public class UserPicServiceImpl implements UserPicService {

	@Autowired
	UserPicDao dao;

	@Override
	public void save(UserPic userPic) {
		dao.save(userPic);

	}

	@Override
	public List<UserPic> findByAlbumId(UserPicAlbum albumId) {
		return dao.findByAlbumId(albumId);
	}

	@Override
	public List<UserPic> findByUser(User User) {
		return dao.findByUser(User);
	}

	@Override
	public UserPic findById(int userPictureId) {
		return dao.findById(userPictureId);
	}

	@Override
	public void deleteById(int userPictureId) {
		dao.deleteById(userPictureId);

	}

}
