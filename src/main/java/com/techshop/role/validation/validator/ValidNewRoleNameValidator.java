package com.techshop.role.validation.validator;

import com.techshop.common.util.ValidatorUtils;
import com.techshop.role.dto.UpdateRoleDto;
import com.techshop.role.service.RoleService;
import com.techshop.role.validation.annotation.ValidNewRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidNewRoleNameValidator implements ConstraintValidator<ValidNewRoleName, UpdateRoleDto> {
	private String message;
	private RoleService service;
	
	public ValidNewRoleNameValidator(RoleService roleService) {
		service = roleService;
	}
	
	@Override
	public void initialize(ValidNewRoleName constraintAnnotation) {
		message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(UpdateRoleDto dto, ConstraintValidatorContext context) {
		if(dto.getName().equals(dto.getOldName())) {
			boolean isTaken = service.isTakenName(dto.getName());
			
			if(!isTaken)
				return true;
		}
		
		ValidatorUtils.addError(context, message);
		return false;
	}

}
