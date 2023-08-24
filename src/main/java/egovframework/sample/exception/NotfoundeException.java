package egovframework.sample.exception;

public class NotfoundeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotfoundeException() {
        super();
    }

    public NotfoundeException(String message) {
        super(message);
    }

    public NotfoundeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotfoundeException(Throwable cause) {
        super(cause);
    }
}
