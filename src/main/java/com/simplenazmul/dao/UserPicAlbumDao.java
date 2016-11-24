package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPicAlbum;

public interface UserPicAlbumDao {

	void save(UserPicAlbum userPicAlbum);

	List<UserPicAlbum> findAllAlbumByUserId(User user);

	String getAlbumCaptionById(UserPicAlbum albumId);

	UserPicAlbum findById(int albumId);

	void deleteAlbumById(UserPicAlbum albumId);

	int findUserPicAlbumByAlbumCaptionAndUser(String albumCaption, User user);
}
