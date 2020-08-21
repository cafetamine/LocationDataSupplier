package com.actilive.lds.core.application.address;

import com.actilive.lds.core.domain.address.Address;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AddressRepository {

    /**
     * Creates AddressEntity and saves it database.
     * If address for given id already exists it will be overwritten.
     *
     * @param address with nullable id.
     * @return saved Address instance with unique id.
     */
    Address save(@NotNull Address address);

    /**
     * Attempts to create AddressEntity and save it in database.
     * If address for given id already exists, will return io.vavr.control.Option.None instead.
     *
     * @param address with nullable id.
     * @return saved Address instance or io.vavr.control.Option.None.
     */
    Option<Address> trySave(@NotNull Address address);

    /**
     * @return List of all Addresses stored in database.
     */
    Set<Address> findAll(); // TODO [future] performance?

    /**
     * @param id cannot be null.
     * @return Address retrieved form database if exists else io.vavr.control.Option.None.
     */
    Option<Address> findById(@NotNull Long id);

    /**
     * Attempts to retrieve Address form database by provided id.
     * If found will overwrite stored entity with provided.
     * If not found will return io.vavr.control.Option.None instead.
     *
     * @param address with updated data (other than id).
     * @return updated Address or io.vavr.control.Option.None.
     */
    Option<Address> update(@NotNull Address address);

    /**
     * Attempts retrieve Address form database by provided id.
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
