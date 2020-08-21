package com.actilive.lds.core.application.location.status;

import com.actilive.lds.core.domain.location.status.LocationStatus;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocationStatusRepository {

    /**
     * Creates LocationStatusEntity and saves it database.
     * If status for given id already exists it will be overwritten.
     *
     * @param status with nullable id.
     * @return saved LocationStatus instance with unique id.
     */
    LocationStatus save(@NotNull LocationStatus status);

    /**
     * Attempts to create LocationStatus and save it in database.
     * If status for given id already exists, will return io.vavr.control.Option.None instead.
     *
     * @param status with nullable id.
     * @return saved LocationStatus instance or io.vavr.control.Option.None.
     */
    Option<LocationStatus> trySave(@NotNull LocationStatus status);

    /**
     * @return List of all LocationAddresses stored in database.
     */
    Set<LocationStatus> findAll(); // TODO [future] performance?

    /**
     * @param id cannot be null.
     * @return LocationStatus retrieved form database if exists else io.vavr.control.Option.None.
     */
    Option<LocationStatus> findById(@NotNull Long id);

    /**
     * Attempts to retrieve LocationStatus form database by provided id.
     * If found will overwrite stored entity with provided.
     * If not found will return io.vavr.control.Option.None instead.
     *
     * @param status with updated data (other than id).
     * @return updated LocationStatus or io.vavr.control.Option.None.
     */
    Option<LocationStatus> update(@NotNull LocationStatus status);

    /**
     * Attempts retrieve LocationStatus form database by provided id.
     * If found will delete this entity from database.
     *
     * @param id cannot be null.
     * @return true if status was deleted otherwise false
     */
    boolean delete(@NotNull Long id);

    /**
     * Checks if status for provided id exists in database.
     *
     * @param id cannot be null.
     * @return true if status exists otherwise false.
     */
    boolean existsById(@Nullable Long id);

}
