package com.actilive.lds.api;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Value
public class ApiError {

    @NotNull
    ApiErrorCode errorCode;

    @NotNull
    @NotBlank
    String errorMessage;

}
