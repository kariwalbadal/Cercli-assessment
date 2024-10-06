package cercli.validation.validators;

import cercli.validation.exceptions.InvalidEmailException;

import java.util.Optional;

public class EmailValidator implements Validator<String> {
    @Override
    public void validate(String email) throws InvalidEmailException {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }
    }

    @Override
    public void validate(Optional<String> email) throws InvalidEmailException {
        if (email.isPresent() && !email.get().contains("@")) {
            throw new InvalidEmailException("Invalid email format");
        }
    }
}

