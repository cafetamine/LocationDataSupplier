package com.actilive.lds.controller.location.address;

import com.actilive.lds.api.location.address.ApiLoactionAddress;
import com.actilive.lds.controller.location.address.adapter.LocationAddressApiAdapter;
import com.actilive.lds.core.application.location.address.LocationAddressError;
import com.actilive.lds.core.application.location.address.LocationAddressFacade;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.location.address.LocationAddressDto;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("lds/api/v1.0/addresses")
public class LocationAddressController {

    private final LocationAddressFacade facade;
    private final AddressResponseResolver resolver;

    public LocationAddressController(@NotNull final LocationAddressFacade facade, @NotNull final AddressResponseResolver resolver) {
        this.facade = facade;
        this.resolver = resolver;
    }

    @GetMapping
    public Set<ApiLoactionAddress> getAll() {
        return LocationAddressApiAdapter.ToApi(facade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") final Long id) {
        final Either<ErrorResult<LocationAddressError>, LocationAddressDto> result = facade.getById(id);
        return resolver.resolve(result, location -> ResponseEntity.ok().body(LocationAddressApiAdapter.ToApi(location)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final ApiLoactionAddress address) {
        final Either<ErrorResult<LocationAddressError>, LocationAddressDto> result = facade.create(LocationAddressApiAdapter.FromApi(address));
        return resolver.resolve(result, __ -> new ResponseEntity<>(HttpStatus.CREATED));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        return facade.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.notFound().build();
    }

}
