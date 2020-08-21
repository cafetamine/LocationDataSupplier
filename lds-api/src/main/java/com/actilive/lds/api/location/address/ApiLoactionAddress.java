package com.actilive.lds.api.location.address;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Value
public class ApiLoactionAddress {

    @NotNull
    @NotBlank
    String country;

    @NotNull
    @NotBlank
    String state;

    @NotNull
    @NotBlank
    String city;

    @NotNull
    @NotBlank
    String street;

    @NotNull
    @NotBlank
    String number;

    @NotNull
    @NotBlank
    String postalCode;

}
