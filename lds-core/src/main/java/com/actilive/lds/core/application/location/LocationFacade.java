package com.actilive.lds.core.application.location;

import com.actilive.lds.core.application.location.command.LocationCommandCreate;
import com.actilive.lds.core.application.location.command.LocationCommandUpdate;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.LocationDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

// TODO [0.0.2-SNAPSHOT - handle failures] update docks
public interface LocationFacade {

    Either<ErrorResult<LocationError>, LocationDto> create(@NotNull LocationCommandCreate location);

    Set<LocationDto> getAll(); // TODO [future] performance?

    Either<ErrorResult<LocationError>, LocationDto> getById(@NotNull Long id);

    Either<ErrorResult<LocationError>, LocationDto> update(@NotNull LocationCommandUpdate location);

    boolean delete(@NotNull Long id);

    boolean existsById(@NotNull Long id);

}
