package com.actilive.lds.repository.location.address;

import com.actilive.lds.core.application.location.address.LocationAddressRepository;
import com.actilive.lds.core.domain.location.address.LoactionAddress;
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
    public LoactionAddress save(@NotNull final LoactionAddress address) {
        return repository.save(LocationAddressEntity.FromDomain(address)).ToDomain();
    }

    @Override
    public Option<LoactionAddress> trySave(@NotNull final LoactionAddress address) {
        return !existsById(address.getId()) ? Option.of(save(address)) : Option.none();
    }

    @Override
    public Set<LoactionAddress> findAll() {
        return HashSet.ofAll(repository.findAll()).map(LocationAddressEntity::ToDomain);
    }

    @Override
    public Option<LoactionAddress> findById(@NotNull final Long id) {
        return Option.ofOptional(repository.findById(id)).map(LocationAddressEntity::ToDomain);
    }

    @Override
    public Option<LoactionAddress> update(@NotNull final LoactionAddress address) {
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
