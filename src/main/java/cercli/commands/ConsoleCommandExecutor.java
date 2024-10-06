package cercli.commands;

import cercli.controllers.EmployeeController;
import cercli.models.Employee;
import cercli.models.EmployeeUpdate;

public class ConsoleCommandExecutor extends CommandExecutor {
    private final EmployeeController employeeController;

    public ConsoleCommandExecutor(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    @Override
    public void addEmployee(Employee employee) throws Exception {
        employeeValidatorService.validate(employee);
        employeeController.addEmployee(employee);
    }

    @Override
    public void updateEmployee(EmployeeUpdate employeeUpdate) throws Exception {
        employeeValidatorService.validate(employeeUpdate);
        employeeController.updateEmployee(employeeUpdate);
    }

    @Override
    public void getEmployee(String employeeId) {
        Employee e = employeeController.getEmployee(employeeId);
        System.out.println(e.toString());
    }

    @Override
    public void getAllEmployees() {
        String s = employeeController.getAllEmployeesAsString();
        System.out.println(s);
    }
}

