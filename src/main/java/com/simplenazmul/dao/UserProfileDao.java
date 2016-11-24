package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
