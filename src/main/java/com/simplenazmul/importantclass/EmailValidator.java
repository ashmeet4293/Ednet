package com.simplenazmul.importantclass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.simplenazmul.customannotation.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// private static final String EMAIL_PATTERN =
	// "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return (validateEmail(email));
	}

	private boolean validateEmail(String email) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(EMAIL_PATTERN);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}