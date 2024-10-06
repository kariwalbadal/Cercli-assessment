package cercli.validation.exceptions;

public class InvalidEmailException extends RuntimeException {
    private final String message;

    public InvalidEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
