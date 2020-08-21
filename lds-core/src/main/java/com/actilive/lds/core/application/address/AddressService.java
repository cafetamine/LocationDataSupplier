package com.actilive.lds.core.application.address;

import com.actilive.lds.core.application.address.command.AddressCreateCommand;
import com.actilive.lds.core.application.address.command.AddressUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.address.Address;
import com.actilive.lds.core.domain.address.AddressDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

public class AddressService implements AddressFacade {

    private final AddressRepository repository;

    public AddressService(@NotNull final AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<ErrorResult<AddressError>, AddressDto> create(@NotNull AddressCreateCommand command) {
        return repository.trySave(Address.Create(command))
                         .map(AddressDto::FromDomain)
                         .toEither(new ErrorResult<>(AddressError.Duplicate, "Address already exists."));
    }

    @Override
    public Set<AddressDto> getAll() {
        return repository.findAll().map(AddressDto::FromDomain);
    }

    @Override
    public Either<ErrorResult<AddressError>, AddressDto> getById(@NotNull Long id) {
        return repository.findById(id)
                         .map(AddressDto::FromDomain)
                         .toEither(new ErrorResult<>(AddressError.NotFound, "Address (id=" + id + ") not found."));
    }

    @Override
    public Either<ErrorResult<AddressError>, AddressDto> update(@NotNull AddressUpdateCommand address) {
        return repository.update(Address.Create(address))
                         .map(AddressDto::FromDomain)
                         .toEither(new ErrorResult<>(AddressError.NotFound, "Address (id=" + address.getId() + ") not found."));
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
