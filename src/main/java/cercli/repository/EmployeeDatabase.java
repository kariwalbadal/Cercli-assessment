package cercli.repository;

import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.repository.exceptions.EmployeeNotFoundException;
import cercli.repository.exceptions.EmployeeUpdateException;

import java.util.List;

public interface EmployeeDatabase {
    Employee addEmployee(Employee employee);

    Employee updateEmployee(EmployeeUpdate employeeUpdate) throws EmployeeNotFoundException, EmployeeUpdateException;

    Employee getEmployee(String employeeId) throws EmployeeNotFoundException;

    List<Employee> getAll();
}
