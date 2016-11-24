package com.simplenazmul.importantclass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplenazmul.customannotation.EmailExistsCheck;
import com.simplenazmul.model.User;
import com.simplenazmul.service.UserService;

@Service
public class EmailCheck implements ConstraintValidator<EmailExistsCheck, String> {

	@Autowired
	UserService userService;

	@Override
	public void initialize(EmailExistsCheck arg0) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {

		if (userService != null) {
			User user = userService.findByEmail(email);
			if (user != null) {
				return false;
			}
		}

		return true;

	}

}
