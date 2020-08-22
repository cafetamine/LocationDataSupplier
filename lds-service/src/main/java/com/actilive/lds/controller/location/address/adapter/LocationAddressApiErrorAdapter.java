package com.actilive.lds.controller.location.address.adapter;

import com.actilive.lds.api.ApiErrorCode;
import com.actilive.lds.core.application.location.address.LocationAddressError;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class LocationAddressApiErrorAdapter {

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum AddressApiErrorMapping {

        LocationAddressNotFound(LocationAddressError.NotFound, ApiErrorCode.LocationAddressNotFound, HttpStatus.NOT_FOUND),
        LocationAddressDuplicate(LocationAddressError.Duplicate, ApiErrorCode.LocationAddressDuplicate, HttpStatus.CONFLICT);

        final LocationAddressError addressError;
        final ApiErrorCode errorCode;
        final HttpStatus httpStatus;

    }

    public static Option<LocationAddressApiErrorAdapter.AddressApiErrorMapping> Of(@NotNull final LocationAddressError error) {
        return Stream.of(LocationAddressApiErrorAdapter.AddressApiErrorMapping.values()).find(mapping -> mapping.addressError == error);
    }

}
