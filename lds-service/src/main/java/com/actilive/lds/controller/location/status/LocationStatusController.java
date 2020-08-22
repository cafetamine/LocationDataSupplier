package com.actilive.lds.controller.location.status;

import com.actilive.lds.api.location.status.ApiLocationStatus;
import com.actilive.lds.controller.location.status.adapter.LocationStatusApiAdapter;
import com.actilive.lds.core.application.location.status.LocationStatusError;
import com.actilive.lds.core.application.location.status.LocationStatusFacade;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.status.LocationStatusDto;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("lds/api/v1.0/statuses")
public class LocationStatusController {

    private final LocationStatusFacade facade;
    private final LocationStatusResponseResolver resolver;

    public LocationStatusController(@NotNull final LocationStatusFacade facade,
                                    @NotNull final LocationStatusResponseResolver resolver) {
        this.facade = facade;
        this.resolver = resolver;
    }

    @GetMapping
    public Set<ApiLocationStatus> getAll() {
        return LocationStatusApiAdapter.ToApi(facade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") final Long id) {
        final Either<ErrorResult<LocationStatusError>, LocationStatusDto> result = facade.getById(id);
        return resolver.resolve(result, location -> ResponseEntity.ok().body(LocationStatusApiAdapter.ToApi(location)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final ApiLocationStatus status) {
        final Either<ErrorResult<LocationStatusError>, LocationStatusDto> result = facade.create(LocationStatusApiAdapter.FromApi(status));
        return resolver.resolve(result, __ -> new ResponseEntity<>(HttpStatus.CREATED));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        return facade.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.notFound().build();
    }

}
