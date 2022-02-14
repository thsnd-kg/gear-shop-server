package com.techshop.role.validation.validator;

import com.techshop.common.util.ValidatorUtils;
import com.techshop.role.service.GroupService;
import com.techshop.role.validation.annotation.ExistsGroupId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsGroupIdValidator implements ConstraintValidator<ExistsGroupId, Long> {
	private String message;
	private GroupService service;
	
	public ExistsGroupIdValidator(GroupService groupService) {
		service = groupService;
	}

	@Override
	public void initialize(ExistsGroupId constraintAnnotation) {
		message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Long groupId, ConstraintValidatorContext context) {
		boolean isExisted = service.isExisted(groupId);
		
		if(isExisted)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
