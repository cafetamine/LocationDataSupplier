package com.actilive.lds.controller.coordinates.adapter;

import com.actilive.lds.api.location.coordinates.ApiLocationCoordinates;
import com.actilive.lds.core.application.location.coordinates.command.LocationCoordinatesCreateCommand;
import com.actilive.lds.core.domain.location.coordinates.LocationCoordinatesDto;
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
