package com.actilive.lds.core.application.location.address;

import com.actilive.lds.core.domain.location.address.LocationAddress;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocationAddressRepository {

    /**
     * Creates LocationAddressEntity and saves it database.
     * If address for given id already exists it will be overwritten.
     *
     * @param address with nullable id.
     * @return saved LocationAddress instance with unique id.
     */
    LocationAddress save(@NotNull LocationAddress address);

    /**
     * Attempts to create LocationAddressEntity and save it in database.
     * If address for given id already exists, will return io.vavr.control.Option.None instead.
     *
     * @param address with nullable id.
     * @return saved LocationAddress instance or io.vavr.control.Option.None.
     */
    Option<LocationAddress> trySave(@NotNull LocationAddress address);

    /**
     * @return List of all LoactionAddresses stored in database.
     */
    Set<LocationAddress> findAll(); // TODO [future] performance?

    /**
     * @param id cannot be null.
     * @return LocationAddress retrieved form database if exists else io.vavr.control.Option.None.
     */
    Option<LocationAddress> findById(@NotNull Long id);

    /**
     * Attempts to retrieve LocationAddress form database by provided id.
     * If found will overwrite stored entity with provided.
     * If not found will return io.vavr.control.Option.None instead.
     *
     * @param address with updated data (other than id).
     * @return updated LocationAddress or io.vavr.control.Option.None.
     */
    Option<LocationAddress> update(@NotNull LocationAddress address);

    /**
     * Attempts retrieve LocationAddress form database by provided id.
     * If found will delete this entity from database.
     *
     * @param id cannot be null.
     * @return true if address was deleted otherwise false
     */
    boolean delete(@NotNull Long id);

    /**
     * Checks if address for provided id exists in database.
     *
     * @param id cannot be null.
     * @return true if address exists otherwise false.
     */
    boolean existsById(@Nullable Long id);

}
