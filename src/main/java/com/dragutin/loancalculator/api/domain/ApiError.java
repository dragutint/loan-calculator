package com.dragutin.loancalculator.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class ApiError {

    private String errorId;

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String debugMessage;

    private List<ApiValidationError> validationErrors;


    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(String errorId,
                    HttpStatus status,
                    String message,
                    Throwable ex) {
        this();
        this.errorId = errorId;
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private void addValidationError(FieldError fieldError) {
        addValidationError(new ApiValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
    }

    private void addValidationError(ApiValidationError validationError) {
        if (validationErrors == null)
            validationErrors = new ArrayList<>();

        validationErrors.add(validationError);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class ApiValidationError {
        public static String NAME = "Validation error";

        private String object;

        private String field;

        private Object rejectedValue;

        private String message;

    }
}