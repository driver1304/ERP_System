package pl.fafrowicz.erpSystem.error;

public final class TaskAlreadyExistException extends RuntimeException {

    public TaskAlreadyExistException() {
        super();
    }

    public TaskAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TaskAlreadyExistException(final String message) {
        super(message);
    }

    public TaskAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}
