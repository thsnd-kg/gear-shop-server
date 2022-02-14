package com.techshop.role.validation.annotation;



import com.techshop.role.validation.validator.ValidDescriptionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidDescriptionValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidDescription {
	public String message() default "Description is not valid.";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
