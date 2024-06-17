package com.validation;

import com.model.User;
import com.validation.annotation.ValidUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class UserValidator implements ConstraintValidator<ValidUser, User> {

    ValidUser constraintAnnotation;

    @Override
    public void initialize(ValidUser constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return switch (user.getGender()) {
            case FEMALE -> user.getAge() >= constraintAnnotation.femaleMinAge();
            case MALE -> user.getAge() >= constraintAnnotation.maleMinAge();
        };
    }
}
