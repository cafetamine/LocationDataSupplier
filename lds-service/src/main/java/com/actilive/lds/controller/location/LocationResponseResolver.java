package com.actilive.lds.controller.location;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.core.application.location.LocationError;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.LocationDto;
import com.actilive.lds.controller.ResponseResolver;
import com.actilive.lds.controller.location.adapter.LocationApiErrorAdapter;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// TODO bean
@Component
public class LocationResponseResolver implements ResponseResolver<LocationDto, LocationError> {

    @Override
    public ResponseEntity<?> resolve(final Either<ErrorResult<LocationError>, LocationDto> result,
                                     final Function<LocationDto, ResponseEntity<?>> successFunction) {
        return result.map(successFunction).getOrElseGet(this::failureFunction);
    }

    private ResponseEntity<?> failureFunction(final ErrorResult<LocationError> errorResult) {
        final LocationApiErrorAdapter.LocationApiErrorMapping mapping = LocationApiErrorAdapter.Of(errorResult.getErrorCode())
                .getOrElseThrow(() -> new IllegalStateException("No mapping found for LocationError " + errorResult.getErrorCode().name()));
        final ApiErrorResponse errorResponse = new ApiErrorResponse(mapping.getErrorCode(), errorResult.getErrorMessage());
        return new ResponseEntity<>(errorResponse, mapping.getHttpStatus());
    }

}
