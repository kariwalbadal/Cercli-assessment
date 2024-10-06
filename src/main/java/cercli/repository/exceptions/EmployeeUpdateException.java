package cercli.repository.exceptions;

public class EmployeeUpdateException extends RuntimeException {

    private final String message;

    public EmployeeUpdateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
