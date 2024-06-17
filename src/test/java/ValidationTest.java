import com.model.Adult;
import com.model.Gender;
import com.model.User;
import com.model.UserGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
import org.hibernate.validator.internal.engine.path.PathImpl;
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
    void checkNullableNameField() {
        Adult adult = buildAdult();
        adult.setAge(20);
        adult.setName(null);


        var validate = validator.validate(adult);

        assertEquals(1, validate.size());
        assertEquals(PathImpl.createPathFromString("name"),
                validate.iterator().next().getPropertyPath());
    }

    @Test
    void checkAgeField() {
        var adult = buildAdult();
        var validate = validator.validate(adult);

        assertEquals(1, validate.size());

        System.out.println(validate);
    }

    @Test
    void checkNullableUserInList() {
        var users = new UserGroup();

        users.addUser(buildAdult());
        users.addUser(buildAdult());
        users.addUser(null);

        var validate  = validator.validate(users);

        assertEquals(1, validate.size());
        System.out.println(validate);
        System.out.println(validate.iterator().next().getPropertyPath());
    }

    private Adult buildAdult() {
        return new Adult(17, Gender.FEMALE, "Andrey");
    }
}
