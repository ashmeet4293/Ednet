package com.simplenazmul.service;

import java.util.List;

import com.simplenazmul.model.UserProfile;

public interface UserProfileService {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);

}
