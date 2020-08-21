package com.actilive.lds.core.application.location.address;

import com.actilive.lds.core.application.location.address.command.LocationAddressCreateCommand;
import com.actilive.lds.core.application.location.address.command.LocationAddressUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.address.LocationAddressDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

public interface LocationAddressFacade {

    Either<ErrorResult<LocationAddressError>, LocationAddressDto> create(@NotNull LocationAddressCreateCommand address);

    Set<LocationAddressDto> getAll(); // TODO [future] performance?

    Either<ErrorResult<LocationAddressError>, LocationAddressDto> getById(@NotNull Long id);

    Either<ErrorResult<LocationAddressError>, LocationAddressDto> update(@NotNull LocationAddressUpdateCommand address);

    boolean delete(@NotNull Long id);

    boolean existsById(@NotNull Long id);

}
