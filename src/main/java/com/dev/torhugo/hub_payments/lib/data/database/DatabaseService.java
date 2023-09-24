package com.dev.torhugo.hub_payments.lib.data.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

/**
 * The interface Database service.
 */
public interface DatabaseService {


    /**
     * Persist.
     *
     * @param query  the query
     * @param object the object
     */
    void persist(final String query, final Object object);

    /**
     * Persist.
     *
     * @param query  the query
     * @param params the params
     */
    void persist(final String query, final SqlParameterSource params);

    /**
     * Retrieve optional.
     *
     * @param <T>          the type parameter
     * @param query        the query
     * @param requiredType the required type
     * @return the optional
     */
    <T> Optional<T> retrieve(final String query, final Class<T> requiredType);

    /**
     * Retrieve optional.
     *
     * @param <T>          the type parameter
     * @param query        the query
     * @param params       the params
     * @param requiredType the required type
     * @return the optional
     */
    <T> Optional<T> retrieve(final String query, final SqlParameterSource params, final Class<T> requiredType);

    /**
     * Retrieve optional.
     *
     * @param <T>       the type parameter
     * @param query     the query
     * @param params    the params
     * @param rowMapper the row mapper
     * @return the optional
     */
    <T> Optional<T> retrieve(final String query, final SqlParameterSource params, final RowMapper<T> rowMapper);

    /**
     * Retrieve List<T>.
     *
     * @param <T>          the type parameter
     * @param query        the query
     * @param params       the params
     * @param requiredType the required type
     * @return List<T>
     */
    <T> List<T> retrieveList(final String query, final SqlParameterSource params, final Class<T> requiredType);

    /**
     * Retrieve List<T></>.
     *
     * @param <T>       the type parameter
     * @param query     the query
     * @param rowMapper the row mapper
     * @return List<T>
     */
    <T> List<T> retrieveList(final String query, final RowMapper<T> rowMapper);

    /**
     * Retrieve List<T>.
     *
     * @param <T>       the type parameter
     * @param query     the query
     * @param params    the params
     * @param rowMapper the row mapper
     * @return List<T>
     */
    <T> List<T> retrieveList(final String query, final SqlParameterSource params, final RowMapper<T> rowMapper);
}
