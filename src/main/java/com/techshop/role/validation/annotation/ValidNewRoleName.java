package com.techshop.role.validation.annotation;



import com.techshop.role.validation.validator.ValidNewRoleNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidNewRoleNameValidator.class)
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidNewRoleName {
	String message() default "Description is not valid.";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
