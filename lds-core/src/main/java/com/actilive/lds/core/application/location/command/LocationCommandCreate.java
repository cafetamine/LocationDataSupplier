package com.actilive.lds.core.application.location.command;

import lombok.Value;

@Value(staticConstructor = "create")
public class LocationCommandCreate {

    String latitude;
    String longitude;
    String altitude;

}
