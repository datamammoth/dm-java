package com.datamammoth.exception;

/** Thrown when a resource is not found (404). */
public class NotFoundException extends DataMammothException {
    public NotFoundException(String message, String requestId, String errorCode) {
        super(message, 404, requestId, errorCode);
    }
}
