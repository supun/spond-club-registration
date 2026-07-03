package com.spond.club.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidationFailureException extends RuntimeException {
    private final Map<String, String> fieldErrors;

    public ValidationFailureException(Map<String, String> fieldErrors) {
        super("Validation failed");
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public static ValidationFailureException singleField(String field, String message) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        fieldErrors.put(field, message);
        return new ValidationFailureException(fieldErrors);
    }
}
