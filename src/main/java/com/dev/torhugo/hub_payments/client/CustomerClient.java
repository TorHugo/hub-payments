package com.dev.torhugo.hub_payments.client;

import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerResponse;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeResponseDTO;

public interface CustomerClient {
    /**
     * Register payment register customer response.
     *
     * @param customerDTO the customer dto
     * @return the payment register customer response
     */
    PaymentRegisterCustomerResponse register(final PaymentRegisterCustomerRequest customerDTO);

    /**
     * Tokenize tokenize response dto.
     *
     * @param tokenization the tokenization
     * @return the tokenize response dto
     */
    TokenizeResponseDTO tokenize(final TokenizeRequestDTO tokenization);
}
