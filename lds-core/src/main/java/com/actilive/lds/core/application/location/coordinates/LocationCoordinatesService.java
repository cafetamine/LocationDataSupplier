package com.actilive.lds.core.application.location.coordinates;

import com.actilive.lds.core.application.location.coordinates.command.LocationCoordinatesCreateCommand;
import com.actilive.lds.core.application.location.coordinates.command.LocationCoordinatesUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.coordinates.LocationCoordinates;
import com.actilive.lds.core.domain.location.coordinates.LocationCoordinatesDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

// TODO [0.0.2-SNAPSHOT] logging peek(() -> ...)
public class LocationCoordinatesService implements LocationCoordinatesFacade {

    private final LocationCoordinatesRepository repository;

    public LocationCoordinatesService(@NotNull final LocationCoordinatesRepository repository) {
        this.repository = repository;
    }

    @Override
    public @NotNull Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> create(@NotNull final LocationCoordinatesCreateCommand command) {
        return repository.trySave(LocationCoordinates.Create(command))
                         .map(LocationCoordinatesDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationCoordinatesError.Duplicate, "LocationCoordinates already exists."));
    }

    @Override
    public @NotNull Set<LocationCoordinatesDto> getAll() {
        return repository.findAll().map(LocationCoordinatesDto::FromDomain);
    }

    @Override
    public Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> getById(@NotNull final Long id) {
        return repository.findById(id)
                         .map(LocationCoordinatesDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationCoordinatesError.NotFound, "LocationCoordinates (id=" + id + ") not found."));
    }

    @Override
    public Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> update(@NotNull final LocationCoordinatesUpdateCommand coordinates) {
        return repository.update(LocationCoordinates.Create(coordinates))
                         .map(LocationCoordinatesDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationCoordinatesError.NotFound, "LocationCoordinates (id=" + coordinates.getId() + ") not found."));
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
