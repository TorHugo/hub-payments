package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;

public interface CustomerRepository {

    /**
     * Retrieve by user id customer model.
     *
     * @param userId the user id
     * @return the customer model
     */
    CustomerModel retrieveByUserId(final Long userId);

    void save(final CustomerModel customerModel);
}
