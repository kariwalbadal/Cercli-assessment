package cercli.validation.validators;

import cercli.validation.exceptions.InvalidEmailException;

import java.util.Optional;

public class EmailValidator implements Validator<String> {
    /**
     * Email validator for Add new Employee functionality
     * @param email
     * @throws InvalidEmailException
     */
    @Override
    public void validate(String email) throws InvalidEmailException {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }
    }

    /**
     * Email validator for Update email field when updating an existing employee
     * @param email
     * @throws InvalidEmailException
     */
    @Override
    public void validate(Optional<String> email) throws InvalidEmailException {
        if (email.isPresent() && !email.get().contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }
    }
}

