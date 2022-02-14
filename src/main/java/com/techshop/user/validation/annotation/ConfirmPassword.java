package com.techshop.user.validation.annotation;

import com.techshop.user.validation.validator.ConfirmPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfirmPassword {
    String message() default "Confirm password is not same with password.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
