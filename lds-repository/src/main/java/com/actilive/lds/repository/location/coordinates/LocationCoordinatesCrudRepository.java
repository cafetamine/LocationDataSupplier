package com.actilive.lds.repository.location.coordinates;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationCoordinatesCrudRepository extends CrudRepository<LocationCoordinatesEntity, Long> {

}
