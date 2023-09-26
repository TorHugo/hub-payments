package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FormPaymentEnum;

/**
 * The interface Customer repository.
 */
public interface CustomerRepository {

    /**
     * Retrieve by user id customer model.
     *
     * @param userId the user id
     * @return the customer model
     */
    CustomerModel retrieveByUserId(final Long userId);

    /**
     * Save.
     *
     * @param customerModel the customer model
     */
    void save(final CustomerModel customerModel);

    /**
     * Retrieve by customer id customer model.
     *
     * @param customer the customer
     * @return the customer model
     */
    CustomerModel retrieveByCustomerId(final String customer);

    /**
     * Update from customer, credit-card token.
     *
     * @param customerModel     the customer model
     * @param creditCardModel   the credit card model
     * @param formPaymentEnum   the formPayment
     */
    void update(final CustomerModel customerModel,
                final CreditCardModel creditCardModel,
                final FormPaymentEnum formPaymentEnum);
}
