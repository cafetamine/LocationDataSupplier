package com.actilive.lds.controller.location.address;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.controller.ResponseResolver;
import com.actilive.lds.controller.location.address.adapter.LocationAddressApiErrorAdapter;
import com.actilive.lds.core.application.location.address.LocationAddressError;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.address.LocationAddressDto;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// TODO bean
@Component
public class AddressResponseResolver implements ResponseResolver<LocationAddressDto, LocationAddressError> {

    @Override
    public ResponseEntity<?> resolve(final Either<ErrorResult<LocationAddressError>, LocationAddressDto> result,
                                     final Function<LocationAddressDto, ResponseEntity<?>> successFunction) {
        return result.map(successFunction).getOrElseGet(this::failureFunction);
    }

    private ResponseEntity<?> failureFunction(final ErrorResult<LocationAddressError> errorResult) {
        final LocationAddressApiErrorAdapter.AddressApiErrorMapping mapping = LocationAddressApiErrorAdapter.Of(errorResult.getErrorCode())
                .getOrElseThrow(() -> new IllegalStateException("No mapping found for AddressError " + errorResult.getErrorCode().name()));
        final ApiErrorResponse errorResponse = new ApiErrorResponse(mapping.getErrorCode(), errorResult.getErrorMessage());
        return new ResponseEntity<>(errorResponse, mapping.getHttpStatus());
    }

}
