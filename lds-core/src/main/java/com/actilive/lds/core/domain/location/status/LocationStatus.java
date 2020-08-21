package com.actilive.lds.core.domain.location.status;

import com.actilive.lds.core.application.location.status.command.LocationStatusCreateCommand;
import com.actilive.lds.core.application.location.status.command.LocationStatusUpdateCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationStatus {

    Long id;
    LocationStatusRead status;
    OffsetDateTime lastReadDateTime;
    OffsetDateTime nextReadDatetime;

    public static LocationStatus Create(final LocationStatusCreateCommand status) {
        return new LocationStatus(null,
                                  status.getStatus(),
                                  status.getLastReadDateTime(),
                                  status.getNextReadDatetime());
    }

    public static LocationStatus Create(final LocationStatusUpdateCommand status) {
        return new LocationStatus(status.getId(),
                                  status.getStatus(),
                                  status.getLastReadDateTime(),
                                  status.getNextReadDatetime());
    }

}
