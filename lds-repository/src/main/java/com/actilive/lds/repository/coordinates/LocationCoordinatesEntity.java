package com.actilive.lds.repository.coordinates;

import com.actilive.lds.core.domain.coordinates.LocationCoordinates;
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
@Table("LOCATION_COORDINATES")
public class LocationCoordinatesEntity {

    @Id
    Long id;
    String latitude;
    String longitude;
    String altitude;

    LocationCoordinates ToDomain() {
        return new LocationCoordinates(id, latitude, longitude, altitude);
    }

    static LocationCoordinatesEntity FromDomain(@NotNull final LocationCoordinates coordinates) {
        return new LocationCoordinatesEntity(coordinates.getId(),
                                             coordinates.getLatitude(),
                                             coordinates.getLongitude(),
                                             coordinates.getAltitude());
    }

}
