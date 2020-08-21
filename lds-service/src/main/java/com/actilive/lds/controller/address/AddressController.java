package com.actilive.lds.controller.address;

import com.actilive.lds.api.address.ApiAddress;
import com.actilive.lds.controller.address.adapter.AddressApiAdapter;
import com.actilive.lds.core.application.address.AddressError;
import com.actilive.lds.core.application.address.AddressFacade;
import com.actilive.lds.core.domain.ErrorResult;
import com.actilive.lds.core.domain.address.AddressDto;
import io.vavr.control.Either;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("lds/api/v1.0/addresses")
public class AddressController {

    private final AddressFacade facade;
    private final AddressResponseResolver resolver;

    public AddressController(@NotNull final AddressFacade facade, @NotNull final AddressResponseResolver resolver) {
        this.facade = facade;
        this.resolver = resolver;
    }

    @GetMapping
    public Set<ApiAddress> getAll() {
        return AddressApiAdapter.ToApi(facade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") final Long id) {
        final Either<ErrorResult<AddressError>, AddressDto> result = facade.getById(id);
        return resolver.resolve(result, location -> ResponseEntity.ok().body(AddressApiAdapter.ToApi(location)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid final ApiAddress address) {
        final Either<ErrorResult<AddressError>, AddressDto> result = facade.create(AddressApiAdapter.FromApi(address));
        return resolver.resolve(result, __ -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        return facade.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.notFound().build();
    }

}
