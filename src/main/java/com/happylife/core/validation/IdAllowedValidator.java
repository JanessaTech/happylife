package com.happylife.core.validation;

import com.happylife.core.annotation.IdAllowed;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class IdAllowedValidator implements ConstraintValidator<IdAllowed, String> {
    private String message;
    @Override
    public void initialize(IdAllowed constraintAnnotation) {
        this.message  = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid = true;
        try{
            UUID.fromString(value);
        }catch (IllegalArgumentException ex){
            valid = false;
        }

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
        return valid;
    }
}
