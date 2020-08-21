package com.actilive.lds.repository.location;

import com.actilive.lds.core.application.location.LocationRepository;
import com.actilive.lds.core.domain.location.Location;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;

public class LocationJdbcRepository implements LocationRepository {

    private final LocationCrudRepository repository;

    public LocationJdbcRepository(@NotNull final LocationCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public Location save(@NotNull final Location location) {
        return repository.save(LocationEntity.FromDomain(location)).ToDomain();
    }

    @Override
    public Option<Location> trySave(@NotNull final Location location) {
        return existsById(location.getId()) ? Option.of(save(location)) : Option.none();
    }

    @Override
    public Set<Location> findAll() {
        return HashSet.ofAll(repository.findAll()).map(LocationEntity::ToDomain);
    }

    @Override
    public Option<Location> findById(@NotNull final Long id) {
        return Option.ofOptional(repository.findById(id))
                     .map(LocationEntity::ToDomain);
    }

    @Override
    public Option<Location> update(@NotNull final Location location) {
        return trySave(location);
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
    public boolean existsById(final Long id) {
        return id != null && repository.existsById(id);
    }

}
