package com.validation;

import com.validation.model.Gender;
import com.validation.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    Validator validator;

    ExecutableValidator executableValidator;

    @BeforeEach
    void setUp() {
        validator = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();
        executableValidator = validator.forExecutables();
    }

    @Test
    void checkAgeField() {
        User user = buildUser();

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        assertEquals(1, validate.size());
        assertEquals(PathImpl.createPathFromString("age"),
                validate.iterator().next().getPropertyPath());

        System.err.println(validate);
    }

    private User buildUser() {
        return User.builder()
                .name("Andrey")
                .age(17)
                .gender(Gender.FEMALE)
                .build();
    }
}
