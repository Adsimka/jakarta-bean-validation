import com.model.Adult;
import com.model.Gender;
import com.model.UserGroup;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    private Validator validator;

    private ExecutableValidator executableValidator;

    private UserGroup users;

    @BeforeEach
    void setUp() {
        validator = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();

        executableValidator = validator.forExecutables();

        users = new UserGroup();
    }

    @Test
    @SneakyThrows
    void checkUserGroupMethod() {
        var getUsers = users.getClass().getMethod("getUsers");

        var validate = executableValidator
                .validateReturnValue(users, getUsers, users.getUsers());

        assertEquals(0, validate.size());
        System.out.println(validate);
    }

    @Test
    @SneakyThrows
    void checkUsersConstructor() {
        Constructor<Adult> constructor = Adult.class
                .getConstructor(int.class, Gender.class, String.class);

        var validate = executableValidator
                .validateConstructorParameters(constructor, new Object[]{17, null, null});

        assertEquals(3, validate.size());
        System.out.println(validate);
    }

    @Test
    @SneakyThrows
    void checkAddUser() {
        Method method = users.getClass()
                .getMethod("addUser", int.class, Gender.class, String.class);

        var validate = executableValidator
                .validateParameters(users, method, new Object[]{17, null, null});

        assertEquals(3, validate.size());
        System.out.println(validate);
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
