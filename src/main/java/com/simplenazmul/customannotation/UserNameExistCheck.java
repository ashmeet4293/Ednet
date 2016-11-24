package com.simplenazmul.customannotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;

import com.simplenazmul.importantclass.UserNameCheck;

@ReportAsSingleViolation
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserNameCheck.class)
@Documented
public @interface UserNameExistCheck {
	String message() default "User Name already exists";

	Class[] groups() default {};

	Class[] payload() default {};
}
