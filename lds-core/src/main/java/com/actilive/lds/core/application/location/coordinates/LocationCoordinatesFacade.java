package com.actilive.lds.core.application.location.coordinates;

import com.actilive.lds.core.application.location.coordinates.command.LocationCoordinatesCreateCommand;
import com.actilive.lds.core.application.location.coordinates.command.LocationCoordinatesUpdateCommand;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.coordinates.LocationCoordinatesDto;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;

// TODO [0.0.2-SNAPSHOT - handle failures] update docks
public interface LocationCoordinatesFacade {

    Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> create(@NotNull LocationCoordinatesCreateCommand coordinates);

    Set<LocationCoordinatesDto> getAll(); // TODO [future] performance?

    Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> getById(@NotNull Long id);

    Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> update(@NotNull LocationCoordinatesUpdateCommand coordinates);

    boolean delete(@NotNull Long id);

    boolean existsById(@NotNull Long id);

}
