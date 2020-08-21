package com.actilive.lds.infrastructure.location.coordinates;

import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesFacade;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesRepository;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesService;
import com.actilive.lds.repository.location.coordinates.LocationCoordinatesCrudRepository;
import com.actilive.lds.repository.location.coordinates.LocationCoordinatesJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationCoordinatesConfiguration {

    @Bean
    LocationCoordinatesRepository locationCoordinatesRepository(final LocationCoordinatesCrudRepository locationCoordinatesCrudRepository) {
        return new LocationCoordinatesJdbcRepository(locationCoordinatesCrudRepository);
    }

    @Bean
    LocationCoordinatesFacade locationCoordinatesFacade(final LocationCoordinatesRepository locationCoordinatesRepository) {
        return new LocationCoordinatesService(locationCoordinatesRepository);
    }

}
