package com.actilive.lds.core.application.address.command;

import lombok.Value;

@Value(staticConstructor = "Create")
public class AddressUpdateCommand {

    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;

}
