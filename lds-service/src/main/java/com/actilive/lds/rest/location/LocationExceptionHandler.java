package com.actilive.lds.rest.location;

import com.actilive.lds.api.ApiError;
import com.actilive.lds.api.ApiErrorCode;
import com.actilive.lds.core.application.location.error.LocationDuplicateOperationException;
import com.actilive.lds.core.application.location.error.LocationNotFoundOperationException;
import com.actilive.lds.core.application.location.error.LocationOperationException;
import com.actilive.lds.rest.ApiErrorCodeAdapter;
import com.actilive.lds.rest.GlobalExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class LocationExceptionHandler {

    @ExceptionHandler(LocationNotFoundOperationException.class)
    public @NotNull ResponseEntity<?> handleException(final @NotNull LocationNotFoundOperationException ex) {
        final String errorMessage = ex.getMessage();
        return mapResponse(ApiErrorCode.NotFound, errorMessage);
    }

    @ExceptionHandler(LocationDuplicateOperationException.class)
    public @NotNull ResponseEntity<?> handleException(final @NotNull LocationDuplicateOperationException ex) {
        final String errorMessage = ex.getMessage();
        return mapResponse(ApiErrorCode.Duplicate, errorMessage);
    }

    @ExceptionHandler(LocationOperationException.class)
    public @NotNull ResponseEntity<?> handleException(final @NotNull LocationOperationException ex) {
        final String errorMessage = ex.getMessage();
        return mapResponse(ApiErrorCode.InternalServerError, errorMessage);
    }

    private @NotNull ResponseEntity<Object> mapResponse(final @NotNull ApiErrorCode errorCode,
                                                        final @NotNull String errorMessage) {
        return new ResponseEntity<>(new ApiError(errorCode, errorMessage), ApiErrorCodeAdapter.getHttpStatus(errorCode));
    }

}
