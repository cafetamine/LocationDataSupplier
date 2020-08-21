package com.actilive.lds.core.application.location.status.command;

import com.actilive.lds.core.domain.location.status.LocationStatusRead;
import lombok.Value;

import java.time.OffsetDateTime;

@Value(staticConstructor = "Create")
public class LocationStatusUpdateCommand {

    Long id;
    LocationStatusRead status;
    OffsetDateTime lastReadDateTime;
    OffsetDateTime nextReadDatetime;

}
