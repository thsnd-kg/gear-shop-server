package com.techshop.role.validation.validator;


import com.techshop.common.util.ValidatorUtils;
import com.techshop.role.service.RoleService;
import com.techshop.role.validation.annotation.ExistsRoleId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsRoleIdValidator implements ConstraintValidator<ExistsRoleId, Long> {
	private String message;
	private RoleService service;
	
	public ExistsRoleIdValidator(RoleService roleService) {
		service = roleService;
	}
	
	@Override
	public void initialize(ExistsRoleId constraintAnnotation) {
		message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Long roleId, ConstraintValidatorContext context) {
		boolean isExisted = service.isExistedId(roleId);
		
		if(isExisted)
			return true;
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
