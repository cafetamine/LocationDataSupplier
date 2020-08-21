package com.actilive.lds.core.application.coordinates.command;

import lombok.Value;

@Value(staticConstructor = "Create")
public class LocationCoordinatesUpdateCommand {

    Long id;
    String latitude;
    String longitude;
    String altitude;

}
