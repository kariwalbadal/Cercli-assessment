package cercli.controllers;

import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.repository.EmployeeDatabase;

import java.util.List;

public class EmployeeController {
    private final EmployeeDatabase employeeDatabase;

    public EmployeeController(EmployeeDatabase employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }

    public void addEmployee(Employee employee) {
        employeeDatabase.addEmployee(employee);
    }

    public void updateEmployee(EmployeeUpdate employeeUpdate) {
        employeeDatabase.updateEmployee(employeeUpdate);
    }

    public Employee getEmployee(String employeeId) {
        return employeeDatabase.getEmployee(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employeeDatabase.getAll();
    }

    public String getAllEmployeesAsString() {
        List<Employee> allEmployees = getAllEmployees();
        StringBuilder sb = new StringBuilder();
        for(Employee e: allEmployees) {
            sb.append(e.toString()).append("--------------------").append("\n\n");
        }
        return sb.toString();
    }
}

