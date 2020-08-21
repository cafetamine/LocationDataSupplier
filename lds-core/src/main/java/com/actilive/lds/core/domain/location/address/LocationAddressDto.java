package com.actilive.lds.core.domain.location.address;

import lombok.Value;

@Value
public class LocationAddressDto {

    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;


    public static LocationAddressDto FromDomain(final LoactionAddress address) {
        return new LocationAddressDto(address.getId(),
                                      address.getCountry(),
                                      address.getState(),
                                      address.getCity(),
                                      address.getStreet(),
                                      address.getNumber(),
                                      address.getPostalCode());
    }

}
