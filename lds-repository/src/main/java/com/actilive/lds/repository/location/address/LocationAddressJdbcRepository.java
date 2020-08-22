package com.actilive.lds.repository.location.address;

import com.actilive.lds.core.application.location.address.LocationAddressRepository;
import com.actilive.lds.core.domain.location.address.LocationAddress;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationAddressJdbcRepository implements LocationAddressRepository {

    private final LocationAddressCrudRepository repository;

    public LocationAddressJdbcRepository(@NotNull final LocationAddressCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public LocationAddress save(@NotNull final LocationAddress address) {
        return repository.save(LocationAddressEntity.FromDomain(address)).ToDomain();
    }

    @Override
    public Option<LocationAddress> trySave(@NotNull final LocationAddress address) {
        return !existsById(address.getId()) ? Option.of(save(address)) : Option.none();
    }

    @Override
    public Set<LocationAddress> findAll() {
        return HashSet.ofAll(repository.findAll()).map(LocationAddressEntity::ToDomain);
    }

    @Override
    public Option<LocationAddress> findById(@NotNull final Long id) {
        return Option.ofOptional(repository.findById(id)).map(LocationAddressEntity::ToDomain);
    }

    @Override
    public Option<LocationAddress> update(@NotNull final LocationAddress address) {
        return trySave(address);
    }

    @Override
    public boolean delete(@NotNull final Long id) {
        if (existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(@Nullable final Long id) {
        return id != null && repository.existsById(id);
    }

}
