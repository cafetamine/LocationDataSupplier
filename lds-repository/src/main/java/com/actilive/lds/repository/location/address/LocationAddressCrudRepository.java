package com.actilive.lds.repository.location.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationAddressCrudRepository extends CrudRepository<LocationAddressEntity, Long> {

}
