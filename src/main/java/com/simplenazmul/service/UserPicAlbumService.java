package com.simplenazmul.service;

import java.util.List;

import com.simplenazmul.model.User;
import com.simplenazmul.model.UserPicAlbum;

public interface UserPicAlbumService {

	void save(UserPicAlbum userPicAlbum);

	List<UserPicAlbum> findAllAlbumByUser(User user);

	String getAlbumCaptionById(UserPicAlbum albumId);

	UserPicAlbum findById(int albumId);

	void deleteAlbumById(UserPicAlbum albumId);

	boolean albumHaveOrNot(User user);

	int findUserPicAlbumByAlbumCaptionAndUser(String albumCaption, User user);
	
	UserPicAlbum findUserPicAlbumClassByAlbumCaptionAndUser(String albumCaption, User user);
}
