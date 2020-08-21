package com.actilive.lds.core.application.location.status;

import com.actilive.lds.core.application.location.status.command.LocationStatusCreateCommand;
import com.actilive.lds.core.application.location.status.command.LocationStatusUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.status.LocationStatus;
import com.actilive.lds.core.domain.location.status.LocationStatusDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

public class LocationStatusService implements LocationStatusFacade {

    private final LocationStatusRepository repository;

    public LocationStatusService(@NotNull final LocationStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<ErrorResult<LocationStatusError>, LocationStatusDto> create(@NotNull LocationStatusCreateCommand command) {
        return repository.trySave(LocationStatus.Create(command))
                .map(LocationStatusDto::FromDomain)
                .toEither(new ErrorResult<>(LocationStatusError.Duplicate, "LocationStatus already exists."));
    }

    @Override
    public Set<LocationStatusDto> getAll() {
        return repository.findAll().map(LocationStatusDto::FromDomain);
    }

    @Override
    public Either<ErrorResult<LocationStatusError>, LocationStatusDto> getById(@NotNull Long id) {
        return repository.findById(id)
                .map(LocationStatusDto::FromDomain)
                .toEither(new ErrorResult<>(LocationStatusError.NotFound, "LocationStatus (id=" + id + ") not found."));
    }

    @Override
    public Either<ErrorResult<LocationStatusError>, LocationStatusDto> update(@NotNull LocationStatusUpdateCommand status) {
        return repository.update(LocationStatus.Create(status))
                .map(LocationStatusDto::FromDomain)
                .toEither(new ErrorResult<>(LocationStatusError.NotFound, "LocationStatus (id=" + status.getId() + ") not found."));
    }

    @Override
    public boolean delete(@NotNull Long id) {
        return repository.delete(id);
    }

    @Override
    public boolean existsById(@NotNull Long id) {
        return repository.existsById(id);
    }
    
}
