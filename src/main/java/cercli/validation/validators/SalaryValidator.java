package cercli.validation.validators;

import cercli.validation.exceptions.InvalidSalaryException;

import java.util.Optional;

public class SalaryValidator implements Validator<Double> {
    @Override
    public void validate(Double salary) throws InvalidSalaryException {
        if (salary == null || salary < 0.0d) {
            throw new InvalidSalaryException("Invalid salary");
        }
    }

    @Override
    public void validate(Optional<Double> salary) throws InvalidSalaryException {
        if (salary.isPresent() && salary.get() < 0.0d) {
            throw new InvalidSalaryException("Invalid salary");
        }
    }
}

