package com.actilive.lds.infrastructure.address;

import com.actilive.lds.core.application.location.address.LocationAddressFacade;
import com.actilive.lds.core.application.location.address.LocationAddressRepository;
import com.actilive.lds.core.application.location.address.LocationAddressService;
import com.actilive.lds.repository.location.address.LocationAddressCrudRepository;
import com.actilive.lds.repository.location.address.LocationAddressJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfiguration {

    @Bean
    LocationAddressRepository addressRepository(final LocationAddressCrudRepository addressCrudRepository) {
        return new LocationAddressJdbcRepository(addressCrudRepository);
    }

    @Bean
    LocationAddressFacade addressFacade(final LocationAddressRepository addressRepository) {
        return new LocationAddressService(addressRepository);
    }

}
