package com.actilive.lds.core.domain.location.address;

import com.actilive.lds.core.application.location.address.command.LocationAddressCreateCommand;
import com.actilive.lds.core.application.location.address.command.LocationAddressUpdateCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationAddress {

    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;

    public static LocationAddress Create(final LocationAddressCreateCommand address) {
        return new LocationAddress(null,
                                   address.getCountry(),
                                   address.getState(),
                                   address.getCity(),
                                   address.getStreet(),
                                   address.getNumber(),
                                   address.getPostalCode());
    }

    public static LocationAddress Create(final LocationAddressUpdateCommand address) {
        return new LocationAddress(address.getId(),
                                   address.getCountry(),
                                   address.getState(),
                                   address.getCity(),
                                   address.getStreet(),
                                   address.getNumber(),
                                   address.getPostalCode());
    }

}
