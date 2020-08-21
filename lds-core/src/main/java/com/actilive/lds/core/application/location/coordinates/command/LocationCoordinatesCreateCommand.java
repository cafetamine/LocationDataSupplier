package com.actilive.lds.core.application.location.coordinates.command;

import lombok.Value;

@Value(staticConstructor = "Create")
public class LocationCoordinatesCreateCommand {

    String latitude;
    String longitude;
    String altitude;

}
