package com.simplenazmul.dao;

import java.util.List;

import com.simplenazmul.model.User;

public interface UserDao {

	void save(User user);

	User findById(int id);

	User findByUserName(String userName);

	void deleteByUserName(String userName);

	boolean login(String email, String password);

	void deleteByUserId(int id);

	User findByEmail(String email);

	User userInformationFromUsername(String userName);

	List<User> findAllUsers();

	void updateUser(User user);

	User findByEdnetId(int ednetUserId);

	List<User> findAllUsersBySearchTerm(String searchTerm);

}
