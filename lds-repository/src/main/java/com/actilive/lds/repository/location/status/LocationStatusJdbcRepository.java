package com.actilive.lds.repository.location.status;

import com.actilive.lds.core.application.location.status.LocationStatusRepository;
import com.actilive.lds.core.domain.location.status.LocationStatus;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;

public class LocationStatusJdbcRepository implements LocationStatusRepository {

    private final LocationStatusCrudRepository repository;

    public LocationStatusJdbcRepository(@NotNull final LocationStatusCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public LocationStatus save(@NotNull final LocationStatus status) {
        return repository.save(LocationStatusEntity.FromDomain(status)).ToDomain();
    }

    @Override
    public Option<LocationStatus> trySave(@NotNull final LocationStatus status) {
        return !existsById(status.getId()) ? Option.of(save(status)) : Option.none();
    }

    @Override
    public Set<LocationStatus> findAll() {
        return HashSet.ofAll(repository.findAll()).map(LocationStatusEntity::ToDomain);
    }

    @Override
    public Option<LocationStatus> findById(@NotNull final Long id) {
        return Option.ofOptional(repository.findById(id)).map(LocationStatusEntity::ToDomain);
    }

    @Override
    public Option<LocationStatus> update(@NotNull final LocationStatus status) {
        return trySave(status);
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
