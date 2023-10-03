package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponseDTO;

/**
 * The interface Payment service.
 */
public interface PaymentService {

    /**
     * This method is responsible for creating a new charge. </br>
     * If it does not retrieve a tokenized card and receives the customer's card information, </br>
     * it performs tokenization of the card and retrieves the token to update the customer in </br>
     * the database and also process the charge via card token.
     *
     * @param paymentRequest object entry.
     * @return {@link PaymentResponseDTO}
     */
    PaymentResponseDTO createPayment(final PaymentRequestDTO paymentRequest);

    /**
     * Retrieve by id payment response dto.
     *
     * @param paymentId the payment id
     * @return {@link PaymentResponseDTO}
     */
    PaymentResponseDTO retrieveById(final String paymentId);
}
