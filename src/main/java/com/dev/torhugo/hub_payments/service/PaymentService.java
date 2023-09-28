package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponse;

public interface PaymentService {

    /**
     * This method is responsible for creating a new charge. </br>
     * If it does not retrieve a tokenized card and receives the customer's card information, </br>
     * it performs tokenization of the card and retrieves the token to update the customer in </br>
     * the database and also process the charge via card token.
     *
     * @param paymentRequest object entry.
     * @return undefined
     */
    PaymentResponse createPayment(final PaymentRequest paymentRequest);
}
