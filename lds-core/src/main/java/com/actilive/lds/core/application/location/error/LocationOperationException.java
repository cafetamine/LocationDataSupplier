package com.actilive.lds.core.application.location.error;

import org.jetbrains.annotations.NotNull;

public class LocationOperationException extends RuntimeException {

    private static final String ErrorMessagePattern = "Location %s.";

    public LocationOperationException(final @NotNull String reason) {
        super(String.format(ErrorMessagePattern, reason));
    }

}
