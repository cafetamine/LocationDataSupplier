package com.actilive.lds.core.application.location;

import com.actilive.lds.core.application.location.command.LocationCommandCreate;
import com.actilive.lds.core.application.location.command.LocationCommandUpdate;
import com.actilive.lds.core.application.location.error.LocationDuplicateOperationException;
import com.actilive.lds.core.application.location.error.LocationNotFoundOperationException;
import com.actilive.lds.core.domain.location.Location;
import com.actilive.lds.core.domain.location.LocationDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

// TODO [0.0.2-SNAPSHOT - handle failures] replace throws with io.vavr.control
// TODO [0.0.2-SNAPSHOT] logging peek(() -> ...)
public class LocationService implements LocationFacade {

    private final LocationRepository repository;

    public LocationService(@NotNull final LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public LocationDto create(@NotNull final LocationCommandCreate location) {
        return repository.trySave(Location.create(location))
                         .map(LocationDto::fromDomain)
                         // TODO currently logic will never find duplicates, this will change when location will be embedded part of actual object
                         .getOrElseThrow(LocationDuplicateOperationException::new);
    }

    @Override
    public Set<LocationDto> getAll() {
        return repository.findAll().map(LocationDto::fromDomain);
    }

    @Override
    public LocationDto getById(@NotNull final Long id) {
        return repository.findById(id)
                         .map(LocationDto::fromDomain)
                         .getOrElseThrow(() -> new LocationNotFoundOperationException(id));
    }

    @Override
    public LocationDto update(@NotNull final LocationCommandUpdate location) {
        return repository.update(Location.create(location))
                         .map(LocationDto::fromDomain)
                         .getOrElseThrow(() -> new LocationNotFoundOperationException(location.getId()));
    }

    @Override
    public boolean delete(@NotNull final Long id) {
        return repository.delete(id);
    }

    @Override
    public boolean existsById(@NotNull final Long id) {
        return repository.existsById(id);
    }

}
