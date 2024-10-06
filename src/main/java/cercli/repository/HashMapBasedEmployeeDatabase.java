package cercli.repository;

import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.repository.exceptions.EmployeeNotFoundException;

import java.util.*;

public class HashMapBasedEmployeeDatabase implements EmployeeDatabase {

    private final Map<UUID, Employee> employeeMap = new HashMap<>();

    @Override
    public Employee addEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(EmployeeUpdate employeeUpdate) throws EmployeeNotFoundException {
        UUID employeeId = employeeUpdate.getEmployeeId();

        if (!employeeMap.containsKey(employeeId)) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        Employee existingEmployee = employeeMap.get(employeeId);

        // Creating a new Employee object with updated fields
        Employee updatedEmployee = new Employee(
                employeeId,
                employeeUpdate.getName().orElse(existingEmployee.getName()),               // Use updated name or keep the original
                employeeUpdate.getPosition().orElse(existingEmployee.getPosition()),       // Use updated position or keep the original
                employeeUpdate.getEmail().orElse(existingEmployee.getEmail()),             // Use updated email or keep the original
                employeeUpdate.getSalary().orElse(existingEmployee.getSalary()),           // Use updated salary or keep the original
                employeeUpdate.getCurrency().orElse(existingEmployee.getCurrency()),       // Use updated currency or keep the original
                employeeUpdate.getCountry().orElse(existingEmployee.getCountry()),         // Use updated country or keep the original
                existingEmployee.getCreatedAt(),                                           // Keep original creation date
                existingEmployee.getCreatedBy(),                                           // Keep original created by
                employeeUpdate.getModifiedAt().orElse(existingEmployee.getModifiedAt()),   // Use updated modifiedAt or keep the original
                employeeUpdate.getModifiedBy().orElse(existingEmployee.getModifiedBy())    // Use updated modifiedBy or keep the original
        );

        // Put the updated employee back in the map
        employeeMap.put(employeeId, updatedEmployee);

        return updatedEmployee;
    }

    @Override
    public Employee getEmployee(String employeeId) throws EmployeeNotFoundException {
        Employee e = employeeMap.getOrDefault(UUID.fromString(employeeId), null);
        if(e == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return e;
    }


    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employeeMap.values());
    }

}
