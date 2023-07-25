package com.example.qtacoapp.register;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserForm> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserForm value, ConstraintValidatorContext context) {

        return value.getPassword().equals(value.getConfirmPassword());
    }
}
