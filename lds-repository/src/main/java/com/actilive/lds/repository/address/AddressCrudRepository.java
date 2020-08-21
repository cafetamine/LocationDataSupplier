package com.actilive.lds.repository.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCrudRepository extends CrudRepository<AddressEntity, Long> {

}
