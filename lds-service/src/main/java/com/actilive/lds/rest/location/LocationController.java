package com.actilive.lds.rest.location;

import com.actilive.lds.api.location.ApiLocation;
import com.actilive.lds.core.application.location.LocationError;
import com.actilive.lds.core.application.location.LocationFacade;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.LocationDto;
import com.actilive.lds.rest.location.adapter.LocationApiAdapter;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("lds/api/v1.0/locations")
public class LocationController {

    private final LocationFacade facade;
    private final LocationResponseResolver resolver;

    public LocationController(@NotNull final LocationFacade facade,
                              @NotNull final LocationResponseResolver resolver) {
        this.facade = facade;
        this.resolver = resolver;
    }

    @GetMapping
    public Set<ApiLocation> getAll() {
        return LocationApiAdapter.ToApi(facade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") final Long id) {
        final Either<ErrorResult<LocationError>, LocationDto> result = facade.getById(id);
        return resolver.resolve(result, location -> ResponseEntity.ok().body(LocationApiAdapter.ToApi(location)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final ApiLocation location) {
        final Either<ErrorResult<LocationError>, LocationDto> result = facade.create(LocationApiAdapter.FromApi(location));
        return resolver.resolve(result, __ -> new ResponseEntity<>(HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        return facade.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
