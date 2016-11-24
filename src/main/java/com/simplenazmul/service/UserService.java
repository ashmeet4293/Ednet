package com.simplenazmul.service;

import java.util.List;

import com.simplenazmul.model.User;

public interface UserService {

	void save(User user);

	User findById(int id);

	User findByUserName(String userName);

	User findByEmail(String email);

	void userRegistration(User user);

	boolean isUserExist(User user);

	boolean isEmailExist(User user);

	List<User> findAllUser();

	void updateUser(User user);

	void deleteUserById(int id);

	boolean userLogin(String email, String password);

	boolean isUserNameExist(String userName);

	User findByEdnetId(int ednetUserId);

	List<User> findAllUsersBySearchTerm(String searchTerm);

}