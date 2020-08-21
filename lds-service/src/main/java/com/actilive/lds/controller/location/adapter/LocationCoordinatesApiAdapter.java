package com.actilive.lds.controller.location.adapter;

import com.actilive.lds.api.coordinates.ApiLocationCoordinates;
import com.actilive.lds.core.application.coordinates.command.LocationCoordinatesCreateCommand;
import com.actilive.lds.core.domain.coordinates.LocationCoordinatesDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

public class LocationCoordinatesApiAdapter {

    public static LocationCoordinatesCreateCommand FromApi(@NotNull final ApiLocationCoordinates coordinates) {
        return LocationCoordinatesCreateCommand.Create(coordinates.getLatitude(),
                                                       coordinates.getLongitude(),
                                                       coordinates.getAltitude());
    }

    public static ApiLocationCoordinates ToApi(@NotNull final LocationCoordinatesDto coordinates) {
        return new ApiLocationCoordinates(coordinates.getLatitude(),
                                          coordinates.getLongitude(),
                                          coordinates.getAltitude());
    }

    public static java.util.Set<ApiLocationCoordinates> ToApi(@NotNull final Set<LocationCoordinatesDto> coordinates) {
        return coordinates.map(LocationCoordinatesApiAdapter::ToApi).toJavaSet();
    }

}
