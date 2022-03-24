package team.foobar.exception;

public class AuthFailException extends RuntimeException {
    public AuthFailException() {
        super();
    }

    public AuthFailException(String message) {
        super(message);
    }

    public AuthFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthFailException(Throwable cause) {
        super(cause);
    }

    protected AuthFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
