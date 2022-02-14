package com.techshop.user.validation.validator;

import com.techshop.common.util.ValidatorUtils;
import com.techshop.user.service.UserService;
import com.techshop.user.validation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private String message;
    private UserService service;

    public UniqueUsernameValidator(UserService userService) {
        service = userService;
    }

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
        message = uniqueUsername.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null)
            return false;

        boolean isTaken = service.isTakenUsername(username);

        if(!isTaken)
            return true;

        ValidatorUtils.addError(context, message);
        return false;
    }

}
