package com.actilive.lds.controller.location.status.adapter;

import com.actilive.lds.api.ApiErrorCode;
import com.actilive.lds.core.application.location.status.LocationStatusError;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class LocationStatusApiErrorAdapter {

    @AllArgsConstructor
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum LocationApiErrorMapping {

        LocationStatusNotFound(LocationStatusError.NotFound, ApiErrorCode.LocationStatusNotFound, HttpStatus.NOT_FOUND),
        LocationStatusDuplicate(LocationStatusError.Duplicate, ApiErrorCode.LocationStatusDuplicate, HttpStatus.CONFLICT);

        final LocationStatusError locationError;
        final ApiErrorCode errorCode;
        final HttpStatus httpStatus;

    }

    public static Option<LocationApiErrorMapping> Of(@NotNull final LocationStatusError error) {
        return Stream.of(LocationApiErrorMapping.values()).find(mapping -> mapping.locationError == error);
    }

}
