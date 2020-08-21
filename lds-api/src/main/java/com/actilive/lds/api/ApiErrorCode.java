package com.actilive.lds.api;

public enum ApiErrorCode {

    // TODO remove
    MarkBadRequest,
    NotFound,
    Duplicate,
    InternalServerError,

    // LocationCoordinates
    LocationCoordinatesNotFound,
    LocationCoordinatesDuplicate,

    // LocationAddress
    LocationAddressNotFound,
    LocationAddressDuplicate;

}
