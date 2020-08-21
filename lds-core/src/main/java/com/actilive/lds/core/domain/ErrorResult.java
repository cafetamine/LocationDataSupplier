package com.actilive.lds.core.domain;

import lombok.Value;

@Value
public class ErrorResult<T extends Enum<T>> {

    T errorCode;
    String errorMessage;

}
