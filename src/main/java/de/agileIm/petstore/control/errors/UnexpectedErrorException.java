package de.agileIm.petstore.control.errors;

public class UnexpectedErrorException extends RuntimeException {
    public UnexpectedErrorException() {
        super();
    }

    public UnexpectedErrorException(String message) {
        super(message);
    }

    public UnexpectedErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedErrorException(Throwable cause) {
        super(cause);
    }

    protected UnexpectedErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
