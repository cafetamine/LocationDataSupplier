package com.actilive.lds.core.domain.address;

import lombok.Value;

@Value
public class AddressDto {

    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;


    public static AddressDto FromDomain(final Address address) {
        return new AddressDto(address.getId(),
                              address.getCountry(),
                              address.getState(),
                              address.getCity(),
                              address.getStreet(),
                              address.getNumber(),
                              address.getPostalCode());
    }

}
