package cercli.validation.exceptions;

public class InvalidSalaryException extends RuntimeException{
    private final String message;

    public InvalidSalaryException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
