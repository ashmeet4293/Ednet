package com.simplenazmul.service;

import java.util.List;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPic;
import com.simplenazmul.model.UserPicAlbum;

public interface UserPicService {

	void save(UserPic userPic);

	List<UserPic> findByAlbumId(UserPicAlbum albumId);

	List<UserPic> findByUser(User User);

	UserPic findById(int userPictureId);

	void deleteById(int userPictureId);
}
