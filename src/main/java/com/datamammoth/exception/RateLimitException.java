package com.datamammoth.exception;

/** Thrown when rate limit is exceeded (429). */
public class RateLimitException extends DataMammothException {
    private final int retryAfterSeconds;

    public RateLimitException(String message, String requestId, int retryAfterSeconds) {
        super(message, 429, requestId, "RATE_LIMITED");
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public int getRetryAfterSeconds() { return retryAfterSeconds; }
}
