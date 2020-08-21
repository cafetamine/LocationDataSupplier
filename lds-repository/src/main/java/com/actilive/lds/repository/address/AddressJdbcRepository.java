package com.actilive.lds.repository.address;

import com.actilive.lds.core.application.address.AddressRepository;
import com.actilive.lds.core.domain.address.Address;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AddressJdbcRepository implements AddressRepository {

    private final AddressCrudRepository repository;

    public AddressJdbcRepository(@NotNull final AddressCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(@NotNull final Address address) {
        return repository.save(AddressEntity.FromDomain(address)).ToDomain();
    }

    @Override
    public Option<Address> trySave(@NotNull final Address address) {
        return existsById(address.getId()) ? Option.of(save(address)) : Option.none();
    }

    @Override
    public Set<Address> findAll() {
        return HashSet.ofAll(repository.findAll()).map(AddressEntity::ToDomain);
    }

    @Override
    public Option<Address> findById(@NotNull final Long id) {
        return Option.ofOptional(repository.findById(id)).map(AddressEntity::ToDomain);
    }

    @Override
    public Option<Address> update(@NotNull final Address address) {
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
