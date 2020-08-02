package com.actilive.lds.core.application.location;

import com.actilive.lds.core.application.location.command.LocationCreateCommand;
import com.actilive.lds.core.application.location.command.LocationUpdateCommand;
import com.actilive.lds.core.domain.location.LocationDto;
import io.vavr.collection.Set;
import org.jetbrains.annotations.NotNull;

// TODO [0.0.2-SNAPSHOT - handle failures] decide on Service logic control - io.vavr.Either/Try or RuntimeExceptions + Spring ExceptionHandlers
// TODO [0.0.2-SNAPSHOT - handle failures] update docks
public interface LocationFacade {

    LocationDto create(@NotNull LocationCreateCommand location); // TODO [0.0.2-SNAPSHOT - handle failures]

    Set<LocationDto> getAll(); // TODO [future] performance?

    LocationDto getById(@NotNull Long id);

    LocationDto update(@NotNull LocationUpdateCommand location);

    boolean delete(@NotNull Long id);

    boolean existsById(@NotNull Long id);

}
