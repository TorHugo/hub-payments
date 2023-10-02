package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;

import java.util.Optional;

/**
 * The interface Store repository.
 */
public interface StoreRepository {

    /**
     * Retrieve by cpf cnpj store model.
     *
     * @param cpfOrCnpj the cpf or cnpj
     * @return the store model
     */
    Optional<StoreModel> retrieveByCpfCnpj(final String cpfOrCnpj);

    /**
     * Insert.
     *
     * @param newStore the new store
     */
    void insert(final StoreModel newStore);

    /**
     * Retrieve by id store model.
     *
     * @param storeId the store id
     * @return the store model
     */
    StoreModel retrieveById(final Long storeId);
}
