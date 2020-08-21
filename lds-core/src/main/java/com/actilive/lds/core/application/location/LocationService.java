package com.actilive.lds.core.application.location;

import com.actilive.lds.core.application.location.command.LocationCommandCreate;
import com.actilive.lds.core.application.location.command.LocationCommandUpdate;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.Location;
import com.actilive.lds.core.domain.location.LocationDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

// TODO [0.0.2-SNAPSHOT] logging peek(() -> ...)
public class LocationService implements LocationFacade {

    private final LocationRepository repository;

    public LocationService(@NotNull final LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public @NotNull Either<ErrorResult<LocationError>, LocationDto> create(@NotNull final LocationCommandCreate command) {
        return repository.trySave(Location.Create(command))
                         .map(LocationDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationError.Duplicate, "Location already exists."));
    }

    @Override
    public @NotNull Set<LocationDto> getAll() {
        return repository.findAll().map(LocationDto::FromDomain);
    }

    @Override
    public Either<ErrorResult<LocationError>, LocationDto> getById(@NotNull final Long id) {
        return repository.findById(id)
                         .map(LocationDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationError.NotFound, "Location (id=" + id + ") not found."));
    }

    @Override
    public Either<ErrorResult<LocationError>, LocationDto> update(@NotNull final LocationCommandUpdate location) {
        return repository.update(Location.Create(location))
                         .map(LocationDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationError.NotFound, "Location (id=" + location.getId() + ") not found."));
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
