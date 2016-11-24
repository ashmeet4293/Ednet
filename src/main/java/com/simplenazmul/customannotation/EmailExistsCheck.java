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
import javax.validation.constraints.Pattern;

@org.hibernate.validator.constraints.Email
@Pattern(regexp = ".+@.+\\..+")
@ReportAsSingleViolation
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = com.simplenazmul.importantclass.EmailCheck.class)
@Documented
public @interface EmailExistsCheck {
	String message() default "Email already exists";

	Class[] groups() default {};

	Class[] payload() default {};
}
