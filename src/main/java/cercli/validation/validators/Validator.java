package cercli.validation.validators;

import java.util.Optional;

public interface Validator<T> {
    void validate(T value) throws Exception;
    void validate(Optional<T> value) throws Exception;
}

