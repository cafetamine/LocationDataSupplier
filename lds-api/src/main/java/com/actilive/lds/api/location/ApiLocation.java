package com.actilive.lds.api.location;

import lombok.Value;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
public class ApiLocation {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$")
    String latitude;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$")
    String longitude;

    @Nullable
    @NotBlank
    // TODO [future] pattern as needed
    String altitude;

}
