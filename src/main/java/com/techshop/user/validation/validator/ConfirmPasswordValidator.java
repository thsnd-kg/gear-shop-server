package com.techshop.user.validation.validator;

import com.techshop.common.util.ValidatorUtils;
import com.techshop.user.dto.ChangePasswordDto;
import com.techshop.user.dto.CreateUserDto;
import com.techshop.user.validation.annotation.ConfirmPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator
        implements ConstraintValidator<ConfirmPassword, CreateUserDto> {
    private String message;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(CreateUserDto dto, ConstraintValidatorContext context) {
        if(dto.getPassword() == null || dto.getConfirmPassword() == null) {
            ValidatorUtils.addError(context, message);
            return false;
        }

        if(dto.getPassword().equals(dto.getConfirmPassword()) )
            return true;

        ValidatorUtils.addError(context, message);
        return false;
    }

}
