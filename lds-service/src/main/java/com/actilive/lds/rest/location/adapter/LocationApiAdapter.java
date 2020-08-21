package com.actilive.lds.rest.location.adapter;

import com.actilive.lds.api.location.ApiLocation;
import com.actilive.lds.core.application.location.command.LocationCommandCreate;
import com.actilive.lds.core.domain.location.LocationDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

public class LocationApiAdapter {

    public static LocationCommandCreate FromApi(@NotNull final ApiLocation location) {
        return LocationCommandCreate.create(location.getLatitude(),
                                            location.getLongitude(),
                                            location.getAltitude());
    }

    public static ApiLocation ToApi(@NotNull final LocationDto location) {
        return new ApiLocation(location.getLatitude(),
                               location.getLongitude(),
                               location.getAltitude());
    }

    public static java.util.Set<ApiLocation> ToApi(@NotNull final Set<LocationDto> locations) {
        return locations.map(LocationApiAdapter::ToApi).toJavaSet();
    }

}
