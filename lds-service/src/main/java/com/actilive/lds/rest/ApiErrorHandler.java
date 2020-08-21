package com.actilive.lds.rest;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.api.ApiErrorCode;
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

@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    private static final String UnknownApiErrorMessage = "Unexpected error occurred.";

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull final MethodArgumentNotValidException ex,
            @NotNull final HttpHeaders headers,
            @NotNull final HttpStatus status,
            @NotNull final WebRequest request) {
        final String errorMessage = ex.getBindingResult()
                                      .getFieldErrors()
                                      .stream()
                                      .map(e -> e.getField() + " " + e.getDefaultMessage())
                                      .collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ApiErrorResponse(ApiErrorCode.MarkBadRequest, errorMessage), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(
            @NotNull final HttpMessageNotReadableException ex,
            @NotNull final HttpHeaders headers,
            @NotNull final HttpStatus status,
            @NotNull final WebRequest request) {
        final String errorMessage = ex.getMessage() != null ? ex.getMessage() : UnknownApiErrorMessage;
        return ApiErrorAdapter.mapResponse(ApiErrorCode.MarkBadRequest, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public @NotNull ResponseEntity<?> handleException(@NotNull final Exception ex) {
        final String errorMessage = ex.getMessage() != null ? ex.getMessage() : UnknownApiErrorMessage;
        return ApiErrorAdapter.mapResponse(ApiErrorCode.InternalServerError, errorMessage);
    }

}
