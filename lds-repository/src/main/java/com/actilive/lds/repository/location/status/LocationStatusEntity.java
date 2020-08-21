package com.actilive.lds.repository.location.status;

import com.actilive.lds.core.domain.location.coordinates.LocationCoordinates;
import com.actilive.lds.core.domain.location.status.LocationStatus;
import com.actilive.lds.core.domain.location.status.LocationStatusRead;
import com.actilive.lds.repository.location.coordinates.LocationCoordinatesEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("LOCATION_STATUS")
public class LocationStatusEntity {

    Long id;
    LocationStatusRead status;
    LocalDateTime lastReadDateTime;
    LocalDateTime nextReadDatetime;

    // TODO handle time zones
    LocationStatus ToDomain() {
        return new LocationStatus(id, status, lastReadDateTime.atOffset(ZoneOffset.UTC), nextReadDatetime.atOffset(ZoneOffset.UTC));
    }

    // TODO handle time zones
    static LocationStatusEntity FromDomain(@NotNull final LocationStatus status) {
        return new LocationStatusEntity(status.getId(),
                                        status.getStatus(),
                                        status.getLastReadDateTime().toLocalDateTime(),
                                        status.getNextReadDatetime().toLocalDateTime());
    }

}
