package com.actilive.lds.controller.location.adapter;

import com.actilive.lds.api.ApiErrorCode;
import com.actilive.lds.core.application.location.LocationError;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class LocationApiErrorAdapter {

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum LocationApiErrorMapping {

        LocationNotFound(LocationError.NotFound, ApiErrorCode.LocationNotFound, HttpStatus.NOT_FOUND),
        LocationDuplicate(LocationError.Duplicate, ApiErrorCode.LocationDuplicate, HttpStatus.CONFLICT);

        final LocationError locationError;
        final ApiErrorCode errorCode;
        final HttpStatus httpStatus;

    }

    public static Option<LocationApiErrorMapping> Of(@NotNull final LocationError error) {
        return Stream.of(LocationApiErrorMapping.values()).find(mapping -> mapping.locationError == error);
    }

}
