package com.actilive.lds.controller.coordinates.adapter;

import com.actilive.lds.api.ApiErrorCode;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesError;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class LocationCoordinatesApiErrorAdapter {

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum LocationApiErrorMapping {

        LocationCoordinatesNotFound(LocationCoordinatesError.NotFound, ApiErrorCode.LocationCoordinatesNotFound, HttpStatus.NOT_FOUND),
        LocationCoordinatesDuplicate(LocationCoordinatesError.Duplicate, ApiErrorCode.LocationCoordinatesDuplicate, HttpStatus.CONFLICT);

        final LocationCoordinatesError locationError;
        final ApiErrorCode errorCode;
        final HttpStatus httpStatus;

    }

    public static Option<LocationApiErrorMapping> Of(@NotNull final LocationCoordinatesError error) {
        return Stream.of(LocationApiErrorMapping.values()).find(mapping -> mapping.locationError == error);
    }

}
