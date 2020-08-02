package com.actilive.lds.core.domain.location;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {

    Long id;
    String latitude;
    String longitude;
    String altitude;

    public static Location create(final String latitude,
                                  final String longitude,
                                  final String altitude) {
        return new Location(null, latitude, longitude, altitude);
    }

}
