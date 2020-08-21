package com.actilive.lds.infrastructure.location.status;

import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesFacade;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesRepository;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesService;
import com.actilive.lds.core.application.location.status.LocationStatusFacade;
import com.actilive.lds.core.application.location.status.LocationStatusRepository;
import com.actilive.lds.core.application.location.status.LocationStatusService;
import com.actilive.lds.repository.location.status.LocationStatusCrudRepository;
import com.actilive.lds.repository.location.status.LocationStatusJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationStatusConfiguration {

    @Bean
    LocationStatusRepository locationStatusRepository(final LocationStatusCrudRepository locationStatusCrudRepository) {
        return new LocationStatusJdbcRepository(locationStatusCrudRepository);
    }

    @Bean
    LocationStatusFacade locationStatusFacade(final LocationStatusRepository locationStatusRepository) {
        return new LocationStatusService(locationStatusRepository);
    }

}
