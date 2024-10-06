package cercli.commands;

import cercli.commands.exceptions.InvalidCommandException;
import cercli.models.Country;
import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.validation.ValidatorService;
import cercli.validation.validators.EmailValidator;
import cercli.validation.validators.SalaryValidator;

import java.util.Scanner;
import java.util.UUID;

public abstract class CommandExecutor {
    Scanner scanner;
    ValidatorService employeeValidatorService;

    public CommandExecutor() {
        scanner = new Scanner(System.in);
        employeeValidatorService = new ValidatorService();
        employeeValidatorService.addValidator("email", new EmailValidator());
        employeeValidatorService.addValidator("salary", new SalaryValidator());
    }

    /**
     * Command to add an employee to the database
     * @param employee object received from the command
     * @throws Exception
     */
    abstract void addEmployee(Employee employee) throws Exception;

    /**
     * Command to Udate employee details
     * @param employeeUpdate object received from the command
     * @throws Exception
     */
    abstract void updateEmployee(EmployeeUpdate employeeUpdate) throws Exception;

    /**
     * Command to retrieve the Employee details based on their ID
     * @param employeeId received from the console input
     * @throws Exception
     */
    abstract void getEmployee(String employeeId) throws Exception;

    /**
     * Command to fetch all Employees data from the database
     * @throws Exception
     */
    abstract void getAllEmployees() throws Exception;

    /**
     *
     * @return {@link EmployeeUpdate} object created from the update data received from the console
     */
    private EmployeeUpdate collectEmployeeUpdateData() {
        System.out.println("Enter Employee ID:");
        UUID employeeId = UUID.fromString(scanner.nextLine());

        EmployeeUpdate update = new EmployeeUpdate(employeeId);

        System.out.println("Enter new Name (leave blank to skip):");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            update.setName(name);
        }

        System.out.println("Enter new Position (leave blank to skip):");
        String position = scanner.nextLine();
        if (!position.isEmpty()) {
            update.setPosition(position);
        }

        System.out.println("Enter new Email (leave blank to skip):");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            update.setEmail(email);
        }

        System.out.println("Enter new Salary (leave blank to skip):");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty()) {
            double salary = Double.parseDouble(salaryInput);
            update.setSalary(salary);
        }

        System.out.println("Enter new Country (leave blank to skip):");
        String countryInput = scanner.nextLine();
        if (!countryInput.isEmpty()) {
            update.setCountry(Country.valueOf(countryInput.toUpperCase()));
        }

        System.out.println("Enter new Currency (leave blank to skip):");
        String currency = scanner.nextLine();
        if (!currency.isEmpty()) {
            update.setCurrency(currency);
        }

        return update;
    }

    /**
     *
     * @return {@link Employee} object created from data received from the console
     */
    private Employee collectEmployeeData() {
        System.out.println("Enter Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Position:");
        String position = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Salary:");
        double salary = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter Country (e.g., USA, UK):");
        Country country = Country.fromString(scanner.nextLine().toUpperCase());

        System.out.println("Enter Currency (e.g., USD, EUR):");
        String currency = scanner.nextLine();

        // Creating and returning the Employee object.
        return new Employee(name, position, email, salary, currency, country);
    }

    /**
     *
     * @return Non-empty Employee ID as String
     * @throws InvalidCommandException when the inserted employee ID is empty string
     */
    private String getEmployeeId() throws InvalidCommandException {
        System.out.println("Enter Employee ID");
        String id = scanner.nextLine();
        if(id.isEmpty()) {
            throw new InvalidCommandException("Blank ID not allowed");
        }
        return id;
    }

    public void runInteractiveConsole() {
        while (true) {
            System.out.println("Enter a command (add, update, get, getall) or 'exit' to quit:");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                String command = input.toLowerCase();
                switch (command) {
                    case "add":
                        addEmployee(collectEmployeeData());
                        break;
                    case "update":
                        updateEmployee(collectEmployeeUpdateData()); // Collect data inside method
                        break;
                    case "get":
                        getEmployee(getEmployeeId()); // Collect data inside method
                        break;
                    case "getall":
                        getAllEmployees();
                        break;
                    default:
                        throw new InvalidCommandException("Invalid Command");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

