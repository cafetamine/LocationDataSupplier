package com.actilive.lds.controller.location;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.core.application.coordinates.LocationCoordinatesError;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.coordinates.LocationCoordinatesDto;
import com.actilive.lds.controller.ResponseResolver;
import com.actilive.lds.controller.location.adapter.LocationCoordinatesApiErrorAdapter;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// TODO bean
@Component
public class LocationCoordinateResponseResolver implements ResponseResolver<LocationCoordinatesDto, LocationCoordinatesError> {

    @Override
    public ResponseEntity<?> resolve(final Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> result,
                                     final Function<LocationCoordinatesDto, ResponseEntity<?>> successFunction) {
        return result.map(successFunction).getOrElseGet(this::failureFunction);
    }

    private ResponseEntity<?> failureFunction(final ErrorResult<LocationCoordinatesError> errorResult) {
        final LocationCoordinatesApiErrorAdapter.LocationApiErrorMapping mapping = LocationCoordinatesApiErrorAdapter.Of(errorResult.getErrorCode())
                .getOrElseThrow(() -> new IllegalStateException("No mapping found for LocationError " + errorResult.getErrorCode().name()));
        final ApiErrorResponse errorResponse = new ApiErrorResponse(mapping.getErrorCode(), errorResult.getErrorMessage());
        return new ResponseEntity<>(errorResponse, mapping.getHttpStatus());
    }

}
