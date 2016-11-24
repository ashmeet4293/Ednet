package com.simplenazmul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplenazmul.dao.UserDao;
import com.simplenazmul.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void save(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	public User findById(int id) {

		return dao.findById(id);
	}

	public User findByUserName(String userName) {

		return dao.findByUserName(userName);
	}

	@Override
	public User findByEmail(String email) {

		return dao.findByEmail(email);
	}

	@Override
	public void userRegistration(User user) {

		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setUserName(user.getUserName());
		user.setEmail(user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setGender(user.getGender());
		// user.setBirthdayDate(user.getBirthdayDate());
		user.setUserLevel(1);

		// if(user.getUserProfiles()!= null){
		// user.setUserProfiles(userProfiledao);
		// }
		user.setUserProfiles(user.getUserProfiles());

		dao.save(user);
	}

	@Override
	public boolean isUserExist(User user) {

		return findByUserName(user.getUserName()) != null;
	}

	@Override
	public boolean isEmailExist(User user) {

		return findByEmail(user.getEmail()) != null;
	}

	@Override
	public List<User> findAllUser() {

		return dao.findAllUsers();
	}

	@Override
	public void updateUser(User user) {
		dao.updateUser(user);

	}

	@Override
	public void deleteUserById(int id) {

		dao.deleteByUserId(id);

	}

	@Override
	public boolean userLogin(String email, String password) {

		if (dao.login(email, password) == true) {
			return true;
		}

		return false;

	}

	@Override
	public boolean isUserNameExist(String userName) {

		if (dao.findByUserName(userName) != null) {
			return true;
		}

		return false;
	}

	@Override
	public User findByEdnetId(int ednetUserId) {
		return dao.findByEdnetId(ednetUserId);
	}

	@Override
	public List<User> findAllUsersBySearchTerm(String searchTerm) {
		return dao.findAllUsersBySearchTerm(searchTerm);
	}

}
