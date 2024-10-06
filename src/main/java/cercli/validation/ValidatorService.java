package cercli.validation;

import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.validation.validators.Validator;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service to separate data validation responsibility
 * Client code doesn't need to worry about how many layers of validations there are and what's the validation logic
 */
public class ValidatorService {
    private final Map<String, Validator<?>> validators;

    public ValidatorService() {
        this.validators = new HashMap<>();
    }

    /**
     * Add a new validator in the list of validators
     * @param fieldName Field we want to validate
     * @param validator Validator instance associated with the field
     */
    public void addValidator(String fieldName, Validator<?> validator) {
        if(!validators.containsKey(fieldName)) {
            validators.put(fieldName, validator);
        } else {
            System.out.println("Validator already present");
        }
    }

    /**
     * Validation for the add employee functionality
     * @param employee
     * @throws Exception
     */
    public void validate(Employee employee) throws Exception {
        for (String field : validators.keySet()) {
            // Construct the getter method name
            String getterMethodName = "get" + capitalizeFirstLetter(field);

            // Retrieve the getter method using reflection
            Method getterMethod = Employee.class.getMethod(getterMethodName);

            // Invoke the method to get the field value
            Object fieldValue = getterMethod.invoke(employee);

            // Cast the validator to the appropriate type and validate the field value
            Validator<Object> validator = (Validator<Object>) validators.get(field);
            validator.validate(fieldValue);
        }
    }

    /**
     * Validation for the {@link EmployeeUpdate} object where fields are optional
     * @param employeeUpdate
     * @throws Exception
     */
    public void validate(EmployeeUpdate employeeUpdate) throws Exception {
        for (String field : validators.keySet()) {
            // Construct the getter method name
            String getterMethodName = "get" + capitalizeFirstLetter(field);

            // Retrieve the getter method using reflection
            Method getterMethod = EmployeeUpdate.class.getMethod(getterMethodName);

            // Invoke the method to get the field value
            Object fieldValue = getterMethod.invoke(employeeUpdate);

            // Cast the validator to the appropriate type and validate the field value
            Validator<Optional<Object>> validator = (Validator<Optional<Object>>) validators.get(field);
            validator.validate((Optional<Optional<Object>>) fieldValue);
        }
    }

    private String capitalizeFirstLetter(String field) {
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}


