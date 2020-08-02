package com.actilive.lds.repository.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationCrudRepository extends CrudRepository<LocationEntity, Long> {

}
