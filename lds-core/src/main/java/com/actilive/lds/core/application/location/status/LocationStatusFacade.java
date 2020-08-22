package com.actilive.lds.core.application.location.status;

import com.actilive.lds.core.application.location.status.command.LocationStatusCreateCommand;
import com.actilive.lds.core.application.location.status.command.LocationStatusUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.status.LocationStatusDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

public interface LocationStatusFacade {

    Either<ErrorResult<LocationStatusError>, LocationStatusDto> create(@NotNull LocationStatusCreateCommand status);

    Set<LocationStatusDto> getAll(); // TODO [future] performance?

    Either<ErrorResult<LocationStatusError>, LocationStatusDto> getById(@NotNull Long id);

    Either<ErrorResult<LocationStatusError>, LocationStatusDto> update(@NotNull LocationStatusUpdateCommand status);

    boolean delete(@NotNull Long id);

    boolean existsById(@NotNull Long id);

}
