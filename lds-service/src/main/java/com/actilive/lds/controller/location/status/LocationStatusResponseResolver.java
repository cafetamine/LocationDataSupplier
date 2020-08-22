package com.actilive.lds.controller.location.status;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.controller.ResponseResolver;
import com.actilive.lds.controller.location.status.adapter.LocationStatusApiErrorAdapter;
import com.actilive.lds.core.application.location.status.LocationStatusError;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.status.LocationStatusDto;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// TODO bean
@Component
public class LocationStatusResponseResolver implements ResponseResolver<LocationStatusDto, LocationStatusError> {

    @Override
    public ResponseEntity<?> resolve(final Either<ErrorResult<LocationStatusError>, LocationStatusDto> result,
                                     final Function<LocationStatusDto, ResponseEntity<?>> successFunction) {
        return result.map(successFunction).getOrElseGet(this::failureFunction);
    }

    private ResponseEntity<?> failureFunction(final ErrorResult<LocationStatusError> errorResult) {
        final LocationStatusApiErrorAdapter.LocationApiErrorMapping mapping = LocationStatusApiErrorAdapter.Of(errorResult.getErrorCode())
                .getOrElseThrow(() -> new IllegalStateException("No mapping found for LocationError " + errorResult.getErrorCode().name()));
        final ApiErrorResponse errorResponse = new ApiErrorResponse(mapping.getErrorCode(), errorResult.getErrorMessage());
        return new ResponseEntity<>(errorResponse, mapping.getHttpStatus());
    }

}
