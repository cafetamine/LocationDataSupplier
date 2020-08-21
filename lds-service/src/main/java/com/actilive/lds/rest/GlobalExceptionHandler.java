package com.actilive.lds.rest;

import com.actilive.lds.api.ApiError;
import com.actilive.lds.api.ApiErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
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
        return mapResponse(ApiErrorCode.MarkBadRequest, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public @NotNull ResponseEntity<?> handleException(final @NotNull Exception ex) {
        final String errorMessage = ex.getMessage() != null ? ex.getMessage() : UnknownApiErrorMessage;
        return mapResponse(ApiErrorCode.InternalServerError, errorMessage);
    }

    private @NotNull ResponseEntity<Object> mapResponse(final @NotNull ApiErrorCode errorCode,
                                                        final @NotNull String errorMessage) {
        return new ResponseEntity<>(new ApiError(errorCode, errorMessage), ApiErrorCodeAdapter.getHttpStatus(errorCode));
    }

    // TODO move
    private static class ApiErrorCodeAdapter {

        private static final Map<ApiErrorCode, HttpStatus> ApiErrorToHttpStatusMap = new HashMap();
        static {
            ApiErrorToHttpStatusMap.put(ApiErrorCode.MarkBadRequest, HttpStatus.BAD_REQUEST);
            ApiErrorToHttpStatusMap.put(ApiErrorCode.InternalServerError, HttpStatus.INTERNAL_SERVER_ERROR);

            Assert.isTrue(ApiErrorToHttpStatusMap.values().size() == ApiErrorCode.values().length, "Not all ApiErrorCode's were registered");
        }

        public static @NotNull HttpStatus getHttpStatus(@NotNull final ApiErrorCode apiCode) {
            return ApiErrorToHttpStatusMap.getOrDefault(apiCode, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
