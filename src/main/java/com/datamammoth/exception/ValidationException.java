package com.datamammoth.exception;

import java.util.List;
import java.util.Map;

/** Thrown when request validation fails (400/422). */
public class ValidationException extends DataMammothException {
    private final List<Map<String, String>> fieldErrors;

    public ValidationException(String message, int statusCode, String requestId, List<Map<String, String>> fieldErrors) {
        super(message, statusCode, requestId, "VALIDATION_FAILED");
        this.fieldErrors = fieldErrors != null ? fieldErrors : List.of();
    }

    public List<Map<String, String>> getFieldErrors() { return fieldErrors; }
}
