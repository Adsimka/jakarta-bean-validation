import com.model.Gender;
import com.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
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
