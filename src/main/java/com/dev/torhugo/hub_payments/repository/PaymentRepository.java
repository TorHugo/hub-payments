package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.PaymentModel;

public interface PaymentRepository {

    /**
     * Insert the new payment.
     *
     * @param paymentModel the payment model
     */
    void insert(final PaymentModel paymentModel);
}
