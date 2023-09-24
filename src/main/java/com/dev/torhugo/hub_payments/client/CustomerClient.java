package com.dev.torhugo.hub_payments.client;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerResponse;

public interface CustomerClient {
    PaymentRegisterCustomerResponse register(final PaymentRegisterCustomerRequest customerDTO);
}
