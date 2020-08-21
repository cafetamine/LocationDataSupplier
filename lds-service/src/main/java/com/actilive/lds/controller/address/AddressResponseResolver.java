package com.actilive.lds.controller.address;

import com.actilive.lds.api.ApiErrorResponse;
import com.actilive.lds.controller.ResponseResolver;
import com.actilive.lds.controller.address.adapter.AddressApiErrorAdapter;
import com.actilive.lds.core.application.address.AddressError;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.address.AddressDto;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

// TODO bean
@Component
public class AddressResponseResolver implements ResponseResolver<AddressDto, AddressError> {

    @Override
    public ResponseEntity<?> resolve(final Either<ErrorResult<AddressError>, AddressDto> result,
                                     final Function<AddressDto, ResponseEntity<?>> successFunction) {
        return result.map(successFunction).getOrElseGet(this::failureFunction);
    }

    private ResponseEntity<?> failureFunction(final ErrorResult<AddressError> errorResult) {
        final AddressApiErrorAdapter.AddressApiErrorMapping mapping = AddressApiErrorAdapter.Of(errorResult.getErrorCode())
                .getOrElseThrow(() -> new IllegalStateException("No mapping found for AddressError " + errorResult.getErrorCode().name()));
        final ApiErrorResponse errorResponse = new ApiErrorResponse(mapping.getErrorCode(), errorResult.getErrorMessage());
        return new ResponseEntity<>(errorResponse, mapping.getHttpStatus());
    }

}
