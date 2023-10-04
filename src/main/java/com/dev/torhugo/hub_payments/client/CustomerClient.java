package com.dev.torhugo.hub_payments.client;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRequestRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentReturnDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.costumer.RegisterCustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.costumer.RegisterCustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeResponseDTO;

public interface CustomerClient {
    /**
     * Register payment register customer response.
     *
     * @param customerDTO the customer dto
     * @return the payment register customer response
     */
    RegisterCustomerResponseDTO register(final RegisterCustomerRequestDTO customerDTO);

    /**
     * Tokenize tokenize response dto.
     *
     * @param tokenization the tokenization
     * @return the tokenize response dto
     */
    TokenizeResponseDTO tokenize(final TokenizeRequestDTO tokenization);

    /**
     * Payment payment return dto.
     *
     * @param paymentDTO the payment dto
     * @return the payment return dto
     */
    PaymentReturnDTO payment(final PaymentDTO paymentDTO);

    /**
     * Processing the refund.
     *
     * @param refund the refund
     */
    PaymentRefundDTO refund(final PaymentRequestRefundDTO refund);
}
