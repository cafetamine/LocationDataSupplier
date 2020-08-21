package com.actilive.lds.core.domain.address;

import com.actilive.lds.core.application.address.command.AddressCreateCommand;
import com.actilive.lds.core.application.address.command.AddressUpdateCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;

    public static Address Create(final AddressCreateCommand address) {
        return new Address(null,
                           address.getCountry(),
                           address.getState(),
                           address.getCity(),
                           address.getStreet(),
                           address.getNumber(),
                           address.getPostalCode());
    }

    public static Address Create(final AddressUpdateCommand address) {
        return new Address(address.getId(),
                           address.getCountry(),
                           address.getState(),
                           address.getCity(),
                           address.getStreet(),
                           address.getNumber(),
                           address.getPostalCode());
    }

}
