package com.actilive.lds.api.address;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Value
public class ApiAddress {

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
