package com.actilive.lds.repository.coordinates;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationCoordinatesCrudRepository extends CrudRepository<LocationCoordinatesEntity, Long> {

}
