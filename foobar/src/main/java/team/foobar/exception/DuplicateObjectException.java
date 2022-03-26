package team.foobar.exception;

public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException() {
        super();
    }

    public DuplicateObjectException(String message) {
        super(message);
    }

    public DuplicateObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateObjectException(Throwable cause) {
        super(cause);
    }

    protected DuplicateObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
