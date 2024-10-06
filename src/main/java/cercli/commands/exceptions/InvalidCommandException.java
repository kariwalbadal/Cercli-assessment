package cercli.commands.exceptions;

public class InvalidCommandException extends RuntimeException {
    private final String message;

    public InvalidCommandException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
