package cercli.repository.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    private final String message;

    public EmployeeNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
