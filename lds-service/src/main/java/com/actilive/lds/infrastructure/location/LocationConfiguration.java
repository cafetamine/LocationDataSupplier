package com.actilive.lds.infrastructure.location;

import com.actilive.lds.core.application.coordinates.LocationCoordinatesFacade;
import com.actilive.lds.core.application.coordinates.LocationCoordinatesRepository;
import com.actilive.lds.core.application.coordinates.LocationCoordinatesService;
import com.actilive.lds.repository.coordinates.LocationCoordinatesCrudRepository;
import com.actilive.lds.repository.coordinates.LocationCoordinatesJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfiguration {

    @Bean
    LocationCoordinatesRepository locationRepository(final LocationCoordinatesCrudRepository locationCoordinatesCrudRepository) {
        return new LocationCoordinatesJdbcRepository(locationCoordinatesCrudRepository);
    }

    @Bean
    LocationCoordinatesFacade locationFacade(final LocationCoordinatesRepository locationRepository) {
        return new LocationCoordinatesService(locationRepository);
    }

}
