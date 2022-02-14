package com.techshop.role.validation.annotation;



import com.techshop.role.validation.validator.UniqueGroupNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueGroupNameValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueGroupName {
	String message() default "Group name has been used.";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
