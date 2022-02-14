package com.techshop.role.validation.validator;


import com.techshop.common.util.ValidatorUtils;
import com.techshop.role.validation.annotation.ValidDescription;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDescriptionValidator implements ConstraintValidator<ValidDescription, String> {
	private String message;
	
	@Override
	public void initialize(ValidDescription constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String description, ConstraintValidatorContext context) {
		// check blank
		if(description == null) {
			ValidatorUtils.addError(context, message);
			return false;
		}
		
		// immutable
		description = description.trim();
		
		// check length
		if(description.length() < 10) {
			ValidatorUtils.addError(context, message);
			return false;
		}
		
		return true;
	}

}
