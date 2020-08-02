package com.actilive.lds.core.domain.location;

import com.actilive.lds.core.application.location.command.LocationCreateCommand;
import com.actilive.lds.core.application.location.command.LocationUpdateCommand;
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


    public static Location create(final LocationCreateCommand location) {
        return new Location(null,
                            location.getLatitude(),
                            location.getLongitude(),
                            location.getAltitude());
    }

    public static Location create(final LocationUpdateCommand location) {
        return new Location(location.getId(),
                            location.getLatitude(),
                            location.getLongitude(),
                            location.getAltitude());
    }

}
