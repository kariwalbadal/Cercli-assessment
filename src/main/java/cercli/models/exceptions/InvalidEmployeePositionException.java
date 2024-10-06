package cercli.models.exceptions;

public class InvalidEmployeePositionException extends RuntimeException {
    private final String message;

    public InvalidEmployeePositionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
