package com.epam.library.dao.db.exceptions;

public class ConnectionWaitTimeoutException extends RuntimeException{
    public ConnectionWaitTimeoutException() {
    }

    public ConnectionWaitTimeoutException(String message) {
        super(message);
    }

    public ConnectionWaitTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionWaitTimeoutException(Throwable cause) {
        super(cause);
    }

}
