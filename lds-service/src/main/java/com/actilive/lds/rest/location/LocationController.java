package com.actilive.lds.rest.location;

import com.actilive.lds.api.location.ApiLocation;
import com.actilive.lds.core.application.location.LocationFacade;
import com.actilive.lds.rest.location.adapter.LocationApiAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1.0/location")
public class LocationController {

    private final LocationFacade facade;

    public LocationController(@NotNull final LocationFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public Set<ApiLocation> getAll() {
        return LocationApiAdapter.toApi(facade.getAll());
    }

    @GetMapping("{id}")
    public ApiLocation getById(@PathVariable("id") final Long id) {
        return LocationApiAdapter.toApi(facade.getById(id));
    }

    @PostMapping
    public ApiLocation create(@RequestBody @Valid final ApiLocation location) {
        return LocationApiAdapter.toApi(facade.create(LocationApiAdapter.fromApi(location)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        return facade.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
