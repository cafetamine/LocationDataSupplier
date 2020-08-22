package com.actilive.lds.controller.location.coordinates;

import com.actilive.lds.api.location.coordinates.ApiLocationCoordinates;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesError;
import com.actilive.lds.core.application.location.coordinates.LocationCoordinatesFacade;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.coordinates.LocationCoordinatesDto;
import com.actilive.lds.controller.location.coordinates.adapter.LocationCoordinatesApiAdapter;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("lds/api/v1.0/coordinates")
public class LocationCoordinatesController {

    private final LocationCoordinatesFacade facade;
    private final LocationCoordinateResponseResolver resolver;

    public LocationCoordinatesController(@NotNull final LocationCoordinatesFacade facade,
                                         @NotNull final LocationCoordinateResponseResolver resolver) {
        this.facade = facade;
        this.resolver = resolver;
    }

    @GetMapping
    public Set<ApiLocationCoordinates> getAll() {
        return LocationCoordinatesApiAdapter.ToApi(facade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") final Long id) {
        final Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> result = facade.getById(id);
        return resolver.resolve(result, location -> ResponseEntity.ok().body(LocationCoordinatesApiAdapter.ToApi(location)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final ApiLocationCoordinates coordinates) {
        final Either<ErrorResult<LocationCoordinatesError>, LocationCoordinatesDto> result = facade.create(LocationCoordinatesApiAdapter.FromApi(coordinates));
        return resolver.resolve(result, __ -> new ResponseEntity<>(HttpStatus.CREATED));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        return facade.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.notFound().build();
    }

}
