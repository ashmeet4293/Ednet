package com.simplenazmul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.UserPicAlbumDao;
import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPicAlbum;

@Service
@Transactional
public class UserPicAlbumServiceImpl implements UserPicAlbumService {

	@Autowired
	UserPicAlbumDao dao;

	@Override
	public void save(UserPicAlbum userPicAlbum) {
		dao.save(userPicAlbum);

	}

	@Override
	public List<UserPicAlbum> findAllAlbumByUser(User user) {
		return dao.findAllAlbumByUserId(user);
	}

	@Override
	public String getAlbumCaptionById(UserPicAlbum albumId) {

		return dao.getAlbumCaptionById(albumId);
	}

	@Override
	public UserPicAlbum findById(int albumId) {
		return dao.findById(albumId);
	}

	@Override
	public void deleteAlbumById(UserPicAlbum albumId) {
		dao.deleteAlbumById(albumId);

	}

	@SuppressWarnings("unused")
	@Override
	public boolean albumHaveOrNot(User user) {
		List<UserPicAlbum> ls = findAllAlbumByUser(user);
		int count = 0;

		for (UserPicAlbum uPA : ls) {
			count = count + 1;
		}

		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public int findUserPicAlbumByAlbumCaptionAndUser(String albumCaption, User user) {
		return dao.findUserPicAlbumByAlbumCaptionAndUser(albumCaption, user);
	}

	@Override
	public UserPicAlbum findUserPicAlbumClassByAlbumCaptionAndUser(String albumCaption, User user) {
		return dao.findUserPicAlbumClassByAlbumCaptionAndUser(albumCaption, user);
	}

}
