package cercli.validation.validators;

import cercli.validation.exceptions.InvalidSalaryException;

import java.util.Optional;

public class SalaryValidator implements Validator<Double> {
    /**
     * Salary validator for Add new Employee Functionality
     * @param salary
     * @throws InvalidSalaryException
     */
    @Override
    public void validate(Double salary) throws InvalidSalaryException {
        if (salary == null || salary < 0.0d) {
            throw new InvalidSalaryException("Invalid salary");
        }
    }

    /**
     * Salary validator for when updating salary of an existing employee
     * @param salary
     * @throws InvalidSalaryException
     */
    @Override
    public void validate(Optional<Double> salary) throws InvalidSalaryException {
        if (salary.isPresent() && salary.get() < 0.0d) {
            throw new InvalidSalaryException("Invalid salary");
        }
    }
}

