package com.actilive.lds.core.application.address;

import com.actilive.lds.core.application.address.command.AddressCreateCommand;
import com.actilive.lds.core.application.address.command.AddressUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.address.AddressDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

public interface AddressFacade {

    Either<ErrorResult<AddressError>, AddressDto> create(@NotNull AddressCreateCommand address);

    Set<AddressDto> getAll(); // TODO [future] performance?

    Either<ErrorResult<AddressError>, AddressDto> getById(@NotNull Long id);

    Either<ErrorResult<AddressError>, AddressDto> update(@NotNull AddressUpdateCommand address);

    boolean delete(@NotNull Long id);

    boolean existsById(@NotNull Long id);

}
