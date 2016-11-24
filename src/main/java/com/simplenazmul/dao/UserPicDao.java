package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPic;
import com.simplenazmul.model.UserPicAlbum;

public interface UserPicDao {

	void save(UserPic userPic);

	List<UserPic> findByAlbumId(UserPicAlbum albumId);

	List<UserPic> findByUser(User User);

	UserPic findById(int userPictureId);

	void deleteById(int userPictureId);

}
