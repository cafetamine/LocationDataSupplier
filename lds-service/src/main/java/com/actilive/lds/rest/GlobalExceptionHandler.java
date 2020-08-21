package com.actilive.lds.rest;

import com.actilive.lds.api.ApiError;
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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String UnknownApiErrorMessage = "Unexpected error occurred.";

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
            final @NotNull MethodArgumentNotValidException ex,
            final @NotNull HttpHeaders headers,
            final @NotNull HttpStatus status,
            final @NotNull WebRequest request) {
        final String errorMessage = ex.getBindingResult()
                                      .getFieldErrors()
                                      .stream()
                                      .map(e -> e.getField() + " " + e.getDefaultMessage())
                                      .collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ApiError(ApiErrorCode.MarkBadRequest, errorMessage), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(
            final @NotNull HttpMessageNotReadableException ex,
            final @NotNull HttpHeaders headers,
            final @NotNull HttpStatus status,
            final @NotNull WebRequest request) {
        final String errorMessage = ex.getMessage() != null ? ex.getMessage() : UnknownApiErrorMessage;
        return ApiErrorAdapter.mapResponse(ApiErrorCode.MarkBadRequest, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public @NotNull ResponseEntity<?> handleException(final @NotNull Exception ex) {
        final String errorMessage = ex.getMessage() != null ? ex.getMessage() : UnknownApiErrorMessage;
        return ApiErrorAdapter.mapResponse(ApiErrorCode.InternalServerError, errorMessage);
    }

}
