package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.PaymentModel;
import com.dev.torhugo.hub_payments.lib.data.domain.StoreModel;

import java.math.BigDecimal;

/**
 * The interface Payment repository.
 */
public interface PaymentRepository {

    /**
     * Insert the new payment.
     *
     * @param paymentModel the payment model
     */
    void insert(final PaymentModel paymentModel);

    /**
     * Validating exists payment store model.
     *
     * @param storeId           the store id
     * @param customer          the customer
     * @param value             the value
     * @param externalReference the external reference
     * @return the store model
     */
    StoreModel validatingExistsPayment(final Long storeId,
                                       final String customer,
                                       final BigDecimal value,
                                       final String externalReference);

    /**
     * Retrieve by id payment model.
     *
     * @param paymentId the payment id
     * @return the payment model
     */
    PaymentModel retrieveById(final String paymentId);

    /**
     * Update payment after refund.
     *
     * @param paymentId the payment id
     * @param status    the status
     */
    void updateRefund(final String paymentId,
                      final String status);
}
