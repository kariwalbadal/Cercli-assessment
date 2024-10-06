package cercli.models.exceptions;

public class UnknownCountryException extends RuntimeException {
    private final String message;

    public UnknownCountryException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
