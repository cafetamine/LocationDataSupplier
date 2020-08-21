package com.actilive.lds.core.application.location.coordinates;

import com.actilive.lds.core.domain.location.coordinates.LocationCoordinates;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocationCoordinatesRepository {

    /**
     * Creates LocationCoordinatesEntity and saves it database.
     * If coordinates for given id already exists it will be overwritten.
     *
     * @param coordinates with nullable id.
     * @return saved LocationCoordinates instance with unique id.
     */
    LocationCoordinates save(@NotNull LocationCoordinates coordinates);

    /**
     * Attempts to create LocationCoordinatesEntity and save it in database.
     * If coordinates for given id already exists, will return io.vavr.control.Option.None instead.
     *
     * @param coordinates with nullable id.
     * @return saved LocationCoordinates instance or io.vavr.control.Option.None.
     */
    Option<LocationCoordinates> trySave(@NotNull LocationCoordinates coordinates);

    /**
     * @return List of all LocationsCoordinates stored in database.
     */
    Set<LocationCoordinates> findAll(); // TODO [future] performance?

    /**
     * @param id cannot be null.
     * @return LocationCoordinates retrieved form database if exists else io.vavr.control.Option.None.
     */
    Option<LocationCoordinates> findById(@NotNull Long id);

    /**
     * Attempts to retrieve LocationCoordinatesEntity form database by provided id.
     * If found will overwrite stored entity with provided.
     * If not found will return io.vavr.control.Option.None instead.
     *
     * @param coordinates with updated data (other than id).
     * @return updated LocationCoordinates or io.vavr.control.Option.None.
     */
    Option<LocationCoordinates> update(@NotNull LocationCoordinates coordinates);

    /**
     * Attempts retrieve LocationCoordinatesEntity form database by provided id.
     * If found will delete this entity from database.
     *
     * @param id cannot be null.
     * @return true if coordinates was deleted otherwise false
     */
    boolean delete(@NotNull Long id);

    /**
     * Checks if coordinates for provided id exists in database.
     *
     * @param id cannot be null.
     * @return true if coordinates exists otherwise false.
     */
    boolean existsById(@Nullable Long id);

}
