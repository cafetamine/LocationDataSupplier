package com.actilive.lds.core.domain.coordinates;

import lombok.Value;

@Value
public class LocationCoordinatesDto {

    Long id;
    String latitude;
    String longitude;
    String altitude;

    public static LocationCoordinatesDto FromDomain(final LocationCoordinates coordinates) {
        return new LocationCoordinatesDto(coordinates.getId(),
                                          coordinates.getLatitude(),
                                          coordinates.getLongitude(),
                                          coordinates.getAltitude());
    }

}
