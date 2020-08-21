package com.actilive.lds.repository.address;

import com.actilive.lds.core.domain.address.Address;
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
@Table("ADDRESS")
public class AddressEntity {

    @Id
    Long id;
    String country;
    String state;
    String city;
    String street;
    String number;
    String postalCode;

    Address ToDomain() {
        return new Address(id, country, state, city, street, number, postalCode);
    }

    static AddressEntity FromDomain(@NotNull final Address address) {
        return new AddressEntity(address.getId(),
                                 address.getCountry(),
                                 address.getState(),
                                 address.getCity(),
                                 address.getStreet(),
                                 address.getNumber(),
                                 address.getPostalCode());
    }

}
