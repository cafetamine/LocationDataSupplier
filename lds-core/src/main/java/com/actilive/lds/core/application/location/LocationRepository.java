package com.actilive.lds.core.application.location;

import com.actilive.lds.core.domain.location.Location;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocationRepository {

    /**
     * Creates LocationEntity and saves it database.
     * If location for given id already exists it will be overwritten.
     *
     * @param location with nullable id.
     * @return saved Location instance with unique id.
     */
    Location save(@NotNull Location location);

    /**
     * Attempts to create LocationEntity and save it in database.
     * If location for given id already exists, will return io.vavr.control.Option.None instead.
     *
     * @param location with nullable id.
     * @return saved Location instance or io.vavr.control.Option.None.
     */
    Option<Location> trySave(@NotNull Location location);

    /**
     * @return List of all Locations stored in database.
     */
    Set<Location> findAll(); // TODO [future] performance?

    /**
     * @param id cannot be null.
     * @return Location retrieved form database if exists else io.vavr.control.Option.None.
     */
    Option<Location> findById(@NotNull Long id);

    /**
     * Attempts to retrieve Location form database by provided id.
     * If found will overwrite stored entity with provided.
     * If not found will return io.vavr.control.Option.None instead.
     *
     * @param location with updated data (other than id).
     * @return updated Location or io.vavr.control.Option.None.
     */
    Option<Location> update(@NotNull Location location);

    /**
     * Attempts retrieve Location form database by provided id.
     * If found will delete this entity from database.
     *
     * @param id cannot be null.
     * @return true if location was deleted otherwise false
     */
    boolean delete(@NotNull Long id);

    /**
     * Checks if location for provided id exists in database.
     *
     * @param id cannot be null.
     * @return true if location exists otherwise false.
     */
    boolean existsById(@Nullable Long id);

}
