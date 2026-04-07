package com.datamammoth.exception;

/** Thrown when authentication or authorization fails (401/403). */
public class AuthException extends DataMammothException {
    public AuthException(String message, int statusCode, String requestId, String errorCode) {
        super(message, statusCode, requestId, errorCode);
    }
}
