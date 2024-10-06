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

    /**
     * Controller method to add an employee to the database
     * @param employee to be added to the database
     */
    public void addEmployee(Employee employee) {
        employeeDatabase.addEmployee(employee);
    }

    /**
     * Controller method to update an employee to the database
     * @param employeeUpdate object with values to be updated in the database
     */
    public void updateEmployee(EmployeeUpdate employeeUpdate) {
        employeeDatabase.updateEmployee(employeeUpdate);
    }

    /**
     * Controller method to get a specific employee
     * @param employeeId ID of employee to be searched
     * @return Record of the found employee
     */
    public Employee getEmployee(String employeeId) {
        return employeeDatabase.getEmployee(employeeId);
    }

    /**
     * Controller method to fetch all employees
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeDatabase.getAll();
    }

    /**
     * Print all employees as a string
     * @return String representation which has all employees in the database
     */
    public String getAllEmployeesAsString() {
        List<Employee> allEmployees = getAllEmployees();
        StringBuilder sb = new StringBuilder();
        for(Employee e: allEmployees) {
            sb.append(e.toString()).append("--------------------").append("\n\n");
        }
        return sb.toString();
    }
}

