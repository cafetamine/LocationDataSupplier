package com.actilive.lds.core.domain.location;

import lombok.Value;

@Value
public class LocationDto {

    Long id;
    String latitude;
    String longitude;
    String altitude;

    public static LocationDto FromDomain(final Location location) {
        return new LocationDto(location.getId(),
                               location.getLatitude(),
                               location.getLongitude(),
                               location.getAltitude());
    }

}
