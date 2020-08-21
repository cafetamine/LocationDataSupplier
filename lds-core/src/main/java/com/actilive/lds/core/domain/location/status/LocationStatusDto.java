package com.actilive.lds.core.domain.location.status;

import com.actilive.lds.core.domain.location.address.LocationAddressDto;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class LocationStatusDto {

    Long id;
    LocationStatusDto status;
    OffsetDateTime lastReadDateTime;
    OffsetDateTime nextReadDatetime;

    public static LocationStatusDto FromDomain(final LocationStatus status) {
        return new LocationStatusDto(status.getId(),
                                     status.getStatus(),
                                     status.getLastReadDateTime(),
                                     status.getNextReadDatetime());
    }

}
