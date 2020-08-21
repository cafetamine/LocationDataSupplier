package com.actilive.lds.repository.location.coordinates;

import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesRepository;
import com.actilive.lds.core.domain.location.coordinates.LocationCoordinates;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;

public class LocationCoordinatesJdbcRepository implements LocationCoordinatesRepository {

    private final LocationCoordinatesCrudRepository repository;

    public LocationCoordinatesJdbcRepository(@NotNull final LocationCoordinatesCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public LocationCoordinates save(@NotNull final LocationCoordinates coordinates) {
        return repository.save(LocationCoordinatesEntity.FromDomain(coordinates)).ToDomain();
    }

    @Override
    public Option<LocationCoordinates> trySave(@NotNull final LocationCoordinates coordinates) {
        return !existsById(coordinates.getId()) ? Option.of(save(coordinates)) : Option.none();
    }

    @Override
    public Set<LocationCoordinates> findAll() {
        return HashSet.ofAll(repository.findAll()).map(LocationCoordinatesEntity::ToDomain);
    }

    @Override
    public Option<LocationCoordinates> findById(@NotNull final Long id) {
        return Option.ofOptional(repository.findById(id))
                     .map(LocationCoordinatesEntity::ToDomain);
    }

    @Override
    public Option<LocationCoordinates> update(@NotNull final LocationCoordinates coordinates) {
        return trySave(coordinates);
    }

    @Override
    public boolean delete(@NotNull final Long id) {
        if (existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(final Long id) {
        return id != null && repository.existsById(id);
    }

}
