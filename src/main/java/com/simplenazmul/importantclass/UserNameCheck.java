package com.simplenazmul.importantclass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplenazmul.customannotation.UserNameExistCheck;
import com.simplenazmul.model.User;
import com.simplenazmul.service.UserService;

@Service
public class UserNameCheck implements ConstraintValidator<UserNameExistCheck, String> {

	@Autowired
	UserService userService;

	@Override
	public void initialize(UserNameExistCheck arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext arg1) {

		if (userService != null) {
			User user = userService.findByUserName(userName);
			if (user != null) {
				return false;
			}
		}

		return true;

	}

}
