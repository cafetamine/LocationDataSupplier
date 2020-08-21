package com.actilive.lds.core.application.location.error;

import org.jetbrains.annotations.NotNull;

public class LocationNotFoundOperationException extends LocationOperationException {

    private static final String ErrorMessagePattern = "(id=%s) not found";

    public LocationNotFoundOperationException(final @NotNull Long locationId) {
        super(String.format(ErrorMessagePattern, locationId));
    }

}
