package com.actilive.lds.controller.address.adapter;

import com.actilive.lds.api.address.ApiAddress;
import com.actilive.lds.core.application.address.command.AddressCreateCommand;
import com.actilive.lds.core.domain.address.AddressDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

public class AddressApiAdapter {

    public static AddressCreateCommand FromApi(@NotNull final ApiAddress address) {
        return AddressCreateCommand.Create(address.getCountry(),
                                           address.getState(),
                                           address.getCity(),
                                           address.getStreet(),
                                           address.getNumber(),
                                           address.getPostalCode());
    }

    public static ApiAddress ToApi(@NotNull final AddressDto address) {
        return new ApiAddress(address.getCountry(),
                              address.getState(),
                              address.getCity(),
                              address.getStreet(),
                              address.getNumber(),
                              address.getPostalCode());
    }

    public static java.util.Set<ApiAddress> ToApi(@NotNull final Set<AddressDto> addresses) {
        return addresses.map(AddressApiAdapter::ToApi).toJavaSet();
    }
}
