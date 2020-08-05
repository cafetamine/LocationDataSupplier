package com.actilive.lds.repository.location;

import com.actilive.lds.core.domain.location.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("LOCATION")
public class LocationEntity {

    @Id
    Long id;
    String latitude;
    String longitude;
    String altitude;

    Location toDomain() {
        return new Location(id, latitude, longitude, altitude);
    }

    static LocationEntity fromDomain(@NotNull final Location location) {
        return new LocationEntity(location.getId(),
                                  location.getLatitude(),
                                  location.getLongitude(),
                                  location.getAltitude());
    }

}
