package com.actilive.lds.infrastructure.location.address;

import com.actilive.lds.core.application.location.address.LocationAddressFacade;
import com.actilive.lds.core.application.location.address.LocationAddressRepository;
import com.actilive.lds.core.application.location.address.LocationAddressService;
import com.actilive.lds.repository.location.address.LocationAddressCrudRepository;
import com.actilive.lds.repository.location.address.LocationAddressJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationAddressConfiguration {

    @Bean
    LocationAddressRepository locationAddressRepository(final LocationAddressCrudRepository locationAddressCrudRepository) {
        return new LocationAddressJdbcRepository(locationAddressCrudRepository);
    }

    @Bean
    LocationAddressFacade addressFacade(final LocationAddressRepository locationAddressRepository) {
        return new LocationAddressService(locationAddressRepository);
    }

}
