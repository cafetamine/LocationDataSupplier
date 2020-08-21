package com.actilive.lds.core.application.location.address;

import com.actilive.lds.core.application.location.address.command.LocationAddressCreateCommand;
import com.actilive.lds.core.application.location.address.command.LocationAddressUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.address.LoactionAddress;
import com.actilive.lds.core.domain.location.address.LocationAddressDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

public class LocationAddressService implements LocationAddressFacade {

    private final LocationAddressRepository repository;

    public LocationAddressService(@NotNull final LocationAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<ErrorResult<LocationAddressError>, LocationAddressDto> create(@NotNull LocationAddressCreateCommand command) {
        return repository.trySave(LoactionAddress.Create(command))
                         .map(LocationAddressDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationAddressError.Duplicate, "LocationAddress already exists."));
    }

    @Override
    public Set<LocationAddressDto> getAll() {
        return repository.findAll().map(LocationAddressDto::FromDomain);
    }

    @Override
    public Either<ErrorResult<LocationAddressError>, LocationAddressDto> getById(@NotNull Long id) {
        return repository.findById(id)
                         .map(LocationAddressDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationAddressError.NotFound, "LocationAddress (id=" + id + ") not found."));
    }

    @Override
    public Either<ErrorResult<LocationAddressError>, LocationAddressDto> update(@NotNull LocationAddressUpdateCommand address) {
        return repository.update(LoactionAddress.Create(address))
                         .map(LocationAddressDto::FromDomain)
                         .toEither(new ErrorResult<>(LocationAddressError.NotFound, "LocationAddress (id=" + address.getId() + ") not found."));
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
