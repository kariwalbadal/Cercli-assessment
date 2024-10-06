package cercli.validation;

import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.validation.validators.Validator;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ValidatorService {
    private final Map<String, Validator<?>> validators;

    public ValidatorService() {
        this.validators = new HashMap<>();
    }

    public void addValidator(String fieldName, Validator<?> validator) {
        if(!validators.containsKey(fieldName)) {
            validators.put(fieldName, validator);
        } else {
            System.out.println("Validator already present");
        }
    }

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


