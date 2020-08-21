package com.actilive.lds.infrastructure.address;

import com.actilive.lds.core.application.address.AddressFacade;
import com.actilive.lds.core.application.address.AddressRepository;
import com.actilive.lds.core.application.address.AddressService;
import com.actilive.lds.repository.address.AddressCrudRepository;
import com.actilive.lds.repository.address.AddressJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfiguration {

    @Bean
    AddressRepository addressRepository(final AddressCrudRepository addressCrudRepository) {
        return new AddressJdbcRepository(addressCrudRepository);
    }

    @Bean
    AddressFacade addressFacade(final AddressRepository addressRepository) {
        return new AddressService(addressRepository);
    }

}
