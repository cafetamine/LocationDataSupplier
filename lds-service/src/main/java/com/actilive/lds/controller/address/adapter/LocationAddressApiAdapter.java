package com.actilive.lds.controller.address.adapter;

import com.actilive.lds.api.address.ApiLoactionAddress;
import com.actilive.lds.core.application.address.command.LocationAddressCreateCommand;
import com.actilive.lds.core.domain.address.LocationAddressDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

public class LocationAddressApiAdapter {

    public static LocationAddressCreateCommand FromApi(@NotNull final ApiLoactionAddress address) {
        return LocationAddressCreateCommand.Create(address.getCountry(),
                                                   address.getState(),
                                                   address.getCity(),
                                                   address.getStreet(),
                                                   address.getNumber(),
                                                   address.getPostalCode());
    }

    public static ApiLoactionAddress ToApi(@NotNull final LocationAddressDto address) {
        return new ApiLoactionAddress(address.getCountry(),
                                      address.getState(),
                                      address.getCity(),
                                      address.getStreet(),
                                      address.getNumber(),
                                      address.getPostalCode());
    }

    public static java.util.Set<ApiLoactionAddress> ToApi(@NotNull final Set<LocationAddressDto> addresses) {
        return addresses.map(LocationAddressApiAdapter::ToApi).toJavaSet();
    }
}
