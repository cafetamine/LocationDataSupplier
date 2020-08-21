package com.actilive.lds.repository.location.address;

import com.actilive.lds.core.domain.location.address.LoactionAddress;
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
@Table("LOCATION_ADDRESS")
public class LocationAddressEntity {

    @Id
    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;

    LoactionAddress ToDomain() {
        return new LoactionAddress(id, country, state, city, street, number, postalCode);
    }

    static LocationAddressEntity FromDomain(@NotNull final LoactionAddress address) {
        return new LocationAddressEntity(address.getId(),
                                         address.getCountry(),
                                         address.getState(),
                                         address.getCity(),
                                         address.getStreet(),
                                         address.getNumber(),
                                         address.getPostalCode());
    }

}
