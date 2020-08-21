package com.actilive.lds.controller.status.adapter;


import com.actilive.lds.api.location.status.ApiLocationStatusRead;
import com.actilive.lds.core.domain.location.status.LocationStatusRead;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class LocationStatusReadApiAdapter {

    private static final Map<LocationStatusRead, ApiLocationStatusRead> LocationStatusReadApiMap = new HashMap<>();
    static {
        LocationStatusReadApiMap.put(LocationStatusRead.Available, ApiLocationStatusRead.Available);
        LocationStatusReadApiMap.put(LocationStatusRead.Unavailable, ApiLocationStatusRead.Unavailable);
        LocationStatusReadApiMap.put(LocationStatusRead.Unknown, ApiLocationStatusRead.Unknown);

        Assert.isTrue(LocationStatusRead.values().length == ApiLocationStatusRead.values().length, "Not all LocationStatusRead are registered");
    }

    public static @NotNull ApiLocationStatusRead ToApi(@NotNull final LocationStatusRead status) {
        return LocationStatusReadApiMap.get(status);
    }

    public static @NotNull LocationStatusRead FromApi(@Nullable final ApiLocationStatusRead status) {
        if (status == null) {
            return LocationStatusRead.Unknown;
        }
        return LocationStatusReadApiMap.entrySet()
                                       .stream()
                                       .filter(entry -> status.equals(entry.getValue()))
                                       .findFirst()
                                       .map(Map.Entry::getKey)
                                       .orElse(LocationStatusRead.Unknown);
    }

}
