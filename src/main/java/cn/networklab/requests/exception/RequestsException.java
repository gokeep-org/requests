package cn.networklab.requests.exception;

public class RequestsException extends RuntimeException {
    private String code;
    private String message;

    public RequestsException() {
    }

    public RequestsException(String message) {
        super(message);
    }

    public RequestsException(Throwable cause) {
        super(cause);
    }

    public RequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
