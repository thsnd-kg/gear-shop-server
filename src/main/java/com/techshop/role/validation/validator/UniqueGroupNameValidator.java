package com.techshop.role.validation.validator;


import com.techshop.common.util.ValidatorUtils;
import com.techshop.role.service.GroupService;
import com.techshop.role.validation.annotation.UniqueGroupName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueGroupNameValidator implements ConstraintValidator<UniqueGroupName, String> {
	private String message;
	private GroupService service;
	
	public UniqueGroupNameValidator(GroupService groupService) {
		service = groupService;
	}
	
	@Override
	public void initialize(UniqueGroupName constraintAnnotation) {
		message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String groupName, ConstraintValidatorContext context) {
		boolean isTaken = service.isTakenName(groupName);
		
		if(!isTaken)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
