package com.actilive.lds.core.domain.coordinates;

import com.actilive.lds.core.application.coordinates.command.LocationCoordinatesCreateCommand;
import com.actilive.lds.core.application.coordinates.command.LocationCoordinatesUpdateCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationCoordinates {

    Long id;
    String latitude;
    String longitude;
    String altitude;


    public static LocationCoordinates Create(final LocationCoordinatesCreateCommand coordinates) {
        return new LocationCoordinates(null,
                                       coordinates.getLatitude(),
                                       coordinates.getLongitude(),
                                       coordinates.getAltitude());
    }

    public static LocationCoordinates Create(final LocationCoordinatesUpdateCommand coordinates) {
        return new LocationCoordinates(coordinates.getId(),
                                       coordinates.getLatitude(),
                                       coordinates.getLongitude(),
                                       coordinates.getAltitude());
    }

}
