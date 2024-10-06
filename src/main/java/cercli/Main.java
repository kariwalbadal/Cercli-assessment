package cercli;

import cercli.commands.CommandExecutor;
import cercli.commands.ConsoleCommandExecutor;
import cercli.controllers.EmployeeController;
import cercli.repository.EmployeeDatabase;
import cercli.repository.PostgresEmployeeDatabase;

public class Main {
    public static void main(String[] args) {
        EmployeeDatabase employeeDatabase = new PostgresEmployeeDatabase(); // or new PostgresEmployeeDatabase()
        EmployeeController employeeController = new EmployeeController(employeeDatabase);

        CommandExecutor commandExecutor = new ConsoleCommandExecutor(employeeController);

        commandExecutor.runInteractiveConsole();
    }
}

