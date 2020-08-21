package com.actilive.lds.controller.address.adapter;

import com.actilive.lds.api.ApiErrorCode;
import com.actilive.lds.core.application.address.AddressError;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class AddressApiErrorAdapter {

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum AddressApiErrorMapping {

        AddressNotFound(AddressError.NotFound, ApiErrorCode.AddressNotFound, HttpStatus.NOT_FOUND),
        AddressDuplicate(AddressError.Duplicate, ApiErrorCode.AddressDuplicate, HttpStatus.CONFLICT);

        final AddressError addressError;
        final ApiErrorCode errorCode;
        final HttpStatus httpStatus;

    }

    public static Option<AddressApiErrorAdapter.AddressApiErrorMapping> Of(@NotNull final AddressError error) {
        return Stream.of(AddressApiErrorAdapter.AddressApiErrorMapping.values()).find(mapping -> mapping.addressError == error);
    }

}
