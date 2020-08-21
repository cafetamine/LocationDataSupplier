package com.actilive.lds.controller;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.api.ApiErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiErrorDefultHandler extends ResponseEntityExceptionHandler {

    private static final String UnknownApiErrorMessage = "Unexpected error occurred.";

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull final MethodArgumentNotValidException ex,
            @NotNull final HttpHeaders headers,
            @NotNull final HttpStatus status,
            @NotNull final WebRequest request) {
        log.warn("Bad request occurred : ", ex);
        return ResponseEntity.badRequest().body(new ApiErrorResponse(ApiErrorCode.MarkBadRequest, PrepareConstraintValidationMessage(ex)));
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(
            @NotNull final HttpMessageNotReadableException ex,
            @NotNull final HttpHeaders headers,
            @NotNull final HttpStatus status,
            @NotNull final WebRequest request) {
        log.warn("Bad request occurred : ", ex);
        final String errorMessage = ex.getMessage() != null ? ex.getMessage() : UnknownApiErrorMessage;
        return ResponseEntity.badRequest().body(new ApiErrorResponse(ApiErrorCode.MarkBadRequest, errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public @NotNull ResponseEntity<ApiErrorResponse> handleException(@NotNull final Exception ex) {
        log.error("Unexpected error occurred : ", ex);
        final ApiErrorResponse errorResponse = new ApiErrorResponse(ApiErrorCode.InternalServerError, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private static @NotNull String PrepareConstraintValidationMessage(@NotNull final MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .map(e -> e.getField() + " " + e.getDefaultMessage())
                 .collect(Collectors.joining(", "));
    }

}
