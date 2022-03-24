package team.foobar.exception;

public class JwtFailException extends RuntimeException {
    public JwtFailException() {
        super();
    }

    public JwtFailException(String message) {
        super(message);
    }

    public JwtFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtFailException(Throwable cause) {
        super(cause);
    }

    protected JwtFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
