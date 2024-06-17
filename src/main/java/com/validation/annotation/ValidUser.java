package com.validation.annotation;

import com.validation.UserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintTarget;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, CONSTRUCTOR, TYPE, FIELD})
@Constraint(validatedBy = {UserParamsValidator.class, UserValidator.class})
@Documented
public @interface ValidUser
{

    String message() default "user_params";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int maleMinAge();

    int femaleMinAge();

    ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;
}
