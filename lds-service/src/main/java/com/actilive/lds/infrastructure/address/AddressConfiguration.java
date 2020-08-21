package com.actilive.lds.infrastructure.address;

import com.actilive.lds.core.application.address.LocationAddressFacade;
import com.actilive.lds.core.application.address.LocationAddressRepository;
import com.actilive.lds.core.application.address.LocationAddressService;
import com.actilive.lds.repository.address.LocationAddressCrudRepository;
import com.actilive.lds.repository.address.LocationAddressJdbcRepository;
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
