package com.datamammoth.exception;

/**
 * Base exception for all DataMammoth SDK errors.
 */
public class DataMammothException extends RuntimeException {

    private final int statusCode;
    private final String requestId;
    private final String errorCode;

    public DataMammothException(String message, int statusCode, String requestId, String errorCode) {
        super(message);
        this.statusCode = statusCode;
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public DataMammothException(String message, int statusCode, String requestId, String errorCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public int getStatusCode() { return statusCode; }
    public String getRequestId() { return requestId; }
    public String getErrorCode() { return errorCode; }
}
