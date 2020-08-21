package com.actilive.lds.controller.status.adapter;

import com.actilive.lds.api.location.status.ApiLocationStatus;
import com.actilive.lds.core.application.location.status.command.LocationStatusCreateCommand;
import com.actilive.lds.core.domain.location.status.LocationStatusDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

public class LocationStatusApiAdapter {

    public static LocationStatusCreateCommand FromApi(@NotNull final ApiLocationStatus status) {
        return LocationStatusCreateCommand.Create(LocationStatusReadApiAdapter.FromApi(status.getStatus()),
                                                  status.getLastReadDatetime(),
                                                  status.getNextReadDatetime());
    }

    public static ApiLocationStatus ToApi(@NotNull final LocationStatusDto status) {
        return new ApiLocationStatus(LocationStatusReadApiAdapter.ToApi(status.getStatus()),
                                     status.getLastReadDateTime(),
                                     status.getNextReadDatetime());
    }

    public static java.util.Set<ApiLocationStatus> ToApi(@NotNull final Set<LocationStatusDto> status) {
        return status.map(LocationStatusApiAdapter::ToApi).toJavaSet();
    }
    
}
