package com.actilive.lds.infrastructure.location;

import com.actilive.lds.core.application.location.LocationFacade;
import com.actilive.lds.core.application.location.LocationRepository;
import com.actilive.lds.core.application.location.LocationService;
import com.actilive.lds.repository.location.LocationCrudRepository;
import com.actilive.lds.repository.location.LocationJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfiguration {

    @Bean
    LocationRepository locationRepository(final LocationCrudRepository locationCrudRepository) {
        return new LocationJdbcRepository(locationCrudRepository);
    }

    @Bean
    LocationFacade locationFacade(final LocationRepository locationRepository) {
        return new LocationService(locationRepository);
    }

}
