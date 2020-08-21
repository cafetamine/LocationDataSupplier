package com.actilive.lds.api.location.status;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@Value
public class ApiLocationStatus {

    @NotNull
    ApiLocationStatus status;

    @Nullable
    OffsetDateTime lastReadDatetime;

    @NotNull
    OffsetDateTime nextReadDatetime;

}
