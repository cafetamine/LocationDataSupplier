package com.actilive.lds.core.application.location.error;

public class LocationDuplicateOperationException extends LocationOperationException {

    private static final String ErrorMessagePattern = "already exists";

    public LocationDuplicateOperationException() {
        super(ErrorMessagePattern);
    }

}
