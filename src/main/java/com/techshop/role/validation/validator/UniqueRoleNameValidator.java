package com.techshop.role.validation.validator;

import com.techshop.role.service.RoleService;
import com.techshop.role.validation.annotation.UniqueRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {
	private String message;
	private RoleService service;
	
	public UniqueRoleNameValidator(RoleService roleService) {
		service = roleService;
	}
	
	@Override
	public void initialize(UniqueRoleName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String roleName, ConstraintValidatorContext context) {
		boolean isTaken = service.isTakenName(roleName);
		
		if(!isTaken)
			return true;
		
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
