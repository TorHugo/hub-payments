package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.RefundModel;

public interface RefundRepository {

    /**
     * Insert.
     *
     * @param refundModel the refund model
     */
    void insert(final RefundModel refundModel);

    /**
     * This method is responsible for updating all refunds with the <b>PENDING</b> status.
     *
     * @param paymentId the payment id
     * @param status    the status
     */
    void updateRefund(final String paymentId,
                      final String status);
}
